package com.manta.kurly_work.binding

import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manta.kurly_work.R
import com.manta.kurly_work.loadImage

@BindingAdapter("srcUrl")
fun ImageView.setSrcUrl(uri: String?) {
    uri?.let {
        loadImage(it, R.color.placeHolder)
    }
}

@BindingAdapter("strike")
fun TextView.setIsStrike(isStrike: Boolean) {
    if (isStrike) {
        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun RecyclerView.bindSubmitList(list: List<Any>?) {
    (adapter as? ListAdapter<Any, *>)?.submitList(list)
}

@BindingAdapter("visible")
fun View.setVisible(isVisible: Boolean){
    if(isVisible){
        visibility = View.VISIBLE
    }else{
        visibility = View.GONE
    }
}