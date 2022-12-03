package com.manta.kurly_work

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState


fun CombinedLoadStates.isLoading() = refresh is LoadState.Loading || append is LoadState.Loading
fun CombinedLoadStates.isError() = refresh is LoadState.Error || append is LoadState.Error
fun CombinedLoadStates.isEndOfPageReached() =
    refresh.endOfPaginationReached || append.endOfPaginationReached

fun CombinedLoadStates.onError(block: (Throwable) -> Unit) = apply {
    val refresh = refresh
    val append = append
    if (refresh is LoadState.Error) {
        block(refresh.error)
    }
    if (append is LoadState.Error) {
        block(append.error)
    }
}

fun CombinedLoadStates.onLoading(block: () -> Unit) = apply {
    if (isLoading()) {
        block()
    }

}
