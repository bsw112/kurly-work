package com.manta.kurly_work.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T : Any>(
    @SerializedName("data")
    val data: List<T>
)