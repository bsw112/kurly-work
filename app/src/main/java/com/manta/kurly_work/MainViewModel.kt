package com.manta.kurly_work

import androidx.lifecycle.ViewModel
import com.manta.kurly_work.network.FetchSectionUiModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val fetchSectionUiModelUseCase: FetchSectionUiModelUseCase
) : ViewModel() {



}