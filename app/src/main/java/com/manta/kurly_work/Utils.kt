package com.manta.kurly_work

import android.widget.ImageView
import androidx.annotation.ColorRes
import com.bumptech.glide.Glide
import kotlin.math.roundToInt


fun ImageView.loadImage(imageUrl: String, @ColorRes placeHolderColorRes: Int) {
    Glide.with(this)
        .load(imageUrl)
        .placeholder(placeHolderColorRes)
        .into(this)
}

fun calculateDiscountRate(originalPrice: Int, discountedPrice: Int): String = runCatching {
    require(originalPrice > 0)
    require(discountedPrice > 0)
    require(originalPrice >= discountedPrice)
    val delta = originalPrice - discountedPrice
    (delta / originalPrice.toFloat() * 100).roundToInt()
}.getOrDefault(0).toString() + "%"

fun formatPrice(price : Int) = "${price}원"
