package com.manta.kurly_work.network.data

import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("title")
    val title: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("Paging")
    val page: Paging?
)

data class Paging(
    @SerializedName("next_page")
    val nextPage: Int
)
