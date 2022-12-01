package com.manta.kurly_work.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.manta.kurly_work.model.SectionUiModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SectionUiModelPagingSource @Inject constructor(
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
            val response: List<SectionUiModel> =
                fetchSectionUiModelUseCase.fetchSectionUiModel(nextPageNumber)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber + 1
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