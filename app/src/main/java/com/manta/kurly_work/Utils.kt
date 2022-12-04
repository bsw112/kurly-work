package com.manta.kurly_work

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.manta.kurly_work.model.BaseModel
import kotlin.math.roundToInt


const val TAG = "kurly_debug"


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

fun <T : BaseModel<T>> createItemCallback(): DiffUtil.ItemCallback<T> {
    return object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.itemCallback.areItemsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.itemCallback.areContentsTheSame(oldItem, newItem)
        }
    }
}


fun formatPrice(price : Int) = "${price}원"
