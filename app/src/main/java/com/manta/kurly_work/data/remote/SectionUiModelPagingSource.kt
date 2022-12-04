package com.manta.kurly_work.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.manta.kurly_work.TAG
import com.manta.kurly_work.model.PagingUiModel
import com.manta.kurly_work.model.SectionUiModel
import retrofit2.HttpException
import java.io.IOException

class SectionUiModelPagingSource(
    private val fetchSectionUiModelUseCase: FetchSectionUiModelUseCase,
) : PagingSource<Int, SectionUiModel>() {
    override fun getRefreshKey(state: PagingState<Int, SectionUiModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1).also {
                Log.d(TAG, "refreshKey :$it")
            }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SectionUiModel> {
        return try {
            val currentPage = params.key ?: 1
            Log.d(TAG, "currentPage :$currentPage")
            val response: PagingUiModel<SectionUiModel> =
                fetchSectionUiModelUseCase.fetchSectionUiModel(currentPage)

            LoadResult.Page(
                data = response.sectionUiModel,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = response.nextPage
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: NullPointerException) {
            LoadResult.Error(e)
        }
    }
}