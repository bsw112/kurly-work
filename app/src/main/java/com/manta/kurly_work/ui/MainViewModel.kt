package com.manta.kurly_work.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.manta.kurly_work.data.remote.FetchSectionUiModelUseCase
import com.manta.kurly_work.data.remote.SectionUiModelPagingSource
import com.manta.kurly_work.model.SectionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchSectionUiModelUseCase: FetchSectionUiModelUseCase,
) : ViewModel() {

    val sectionUiModelList: Flow<PagingData<SectionUiModel>> =
        Pager(PagingConfig(3, initialLoadSize = 5)) {
            SectionUiModelPagingSource(fetchSectionUiModelUseCase)
        }.flow.cachedIn(viewModelScope)
}