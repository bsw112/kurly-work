package com.manta.kurly_work.data.remote.entity

import com.google.gson.annotations.SerializedName


data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("originalPrice")
    val originalPrice: Int,
    @SerializedName("discountedPrice")
    val discountedPrice: Int?,
    @SerializedName("isSoldOut")
    val isSoldOut: Boolean
) {
    val finalPrice: Int get() = discountedPrice ?: originalPrice
}
