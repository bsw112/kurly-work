package com.manta.kurly_work.binding

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("strike")
fun TextView.setIsStrike(isStrike: Boolean) {
    if (isStrike) {
        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
}
