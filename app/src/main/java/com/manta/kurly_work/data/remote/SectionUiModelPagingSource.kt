package com.manta.kurly_work.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.manta.kurly_work.model.SectionUiModel
import com.manta.kurly_work.model.PagingUiModel
import retrofit2.HttpException
import java.io.IOException

class SectionUiModelPagingSource(
    private val fetchSectionUiModelUseCase: FetchSectionUiModelUseCase,
) : PagingSource<Int, SectionUiModel>() {
    override fun getRefreshKey(state: PagingState<Int, SectionUiModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SectionUiModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response: PagingUiModel<SectionUiModel> =
                fetchSectionUiModelUseCase.fetchSectionUiModel(nextPageNumber)

            LoadResult.Page(
                data = response.sectionUiModel,
                prevKey = null,
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