package com.manta.kurly_work.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView

open class AppViewHolder<T : Any>(val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T, bindingId: Int) {
        binding.setVariable(bindingId, item)
        binding.executePendingBindings()
    }

    inline fun <reified VH : ViewDataBinding> applyBinding(block: VH.() -> Unit): AppViewHolder<T> {
        if (binding is VH) {
            block(binding)
        }
        return this
    }
}

fun <T : Any> createViewHolder(
    @LayoutRes layoutId: Int,
    parent: ViewGroup
): AppViewHolder<T> {
    val binding = DataBindingUtil.inflate<ViewDataBinding>(
        LayoutInflater.from(parent.context),
        layoutId,
        parent,
        false
    )
    binding.lifecycleOwner = ViewTreeLifecycleOwner.get(parent)
    return AppViewHolder(binding)
}