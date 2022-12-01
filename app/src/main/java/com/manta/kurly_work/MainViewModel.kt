package com.manta.kurly_work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.manta.kurly_work.model.SectionUiModel
import com.manta.kurly_work.network.SectionUiModelPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sectionUiModelPagingSource: SectionUiModelPagingSource
) : ViewModel() {

    val sectionUiModelList: Flow<PagingData<SectionUiModel>> =
        Pager(PagingConfig(3, initialLoadSize = 5)) {
            sectionUiModelPagingSource
        }.flow.cachedIn(viewModelScope)



}