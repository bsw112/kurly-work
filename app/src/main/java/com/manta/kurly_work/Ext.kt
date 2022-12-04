package com.manta.kurly_work

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState


fun CombinedLoadStates.isLoading() = refresh is LoadState.Loading || append is LoadState.Loading || prepend is LoadState.Loading

fun CombinedLoadStates.onError(block: (Throwable) -> Unit) = apply {
    val refresh = refresh
    val append = append
    val prepend = prepend
    if (refresh is LoadState.Error) {
        block(refresh.error)
    }
    if (append is LoadState.Error) {
        block(append.error)
    }
    if(prepend is LoadState.Error){
        block(prepend.error)
    }
}

fun CombinedLoadStates.onLoading(block: () -> Unit) = apply {
    if (isLoading()) {
        block()
    }

}
