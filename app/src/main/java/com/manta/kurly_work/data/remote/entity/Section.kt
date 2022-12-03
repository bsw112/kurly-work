package com.manta.kurly_work.data.remote.entity

import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("title")
    val title: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)
