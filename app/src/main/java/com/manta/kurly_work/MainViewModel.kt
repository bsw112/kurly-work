package com.manta.kurly_work

import androidx.lifecycle.ViewModel
import com.manta.kurly_work.network.KurlyService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val kurlyService: KurlyService,
    val ioDispatcher : CoroutineDispatcher
) : ViewModel() {



}