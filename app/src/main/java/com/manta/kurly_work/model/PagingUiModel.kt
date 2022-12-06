package com.manta.kurly_work.model

data class PagingUiModel<T : BaseModel<T>>(
    val nextPage: Int?,
    val sectionUiModel: List<T>
)