package com.manta.kurly_work.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView

class AppViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Any, bindingId: Int) {
        binding.setVariable(bindingId, item)
        binding.executePendingBindings()
    }

    inline fun <reified VH : ViewDataBinding> applyBinding(block: VH.() -> Unit): AppViewHolder {
        if (binding is VH) {
            block(binding)
        }
        return this
    }
}

fun createAppViewHolder(
    @LayoutRes layoutId: Int,
    parent: ViewGroup
): AppViewHolder {
    val binding = DataBindingUtil.inflate<ViewDataBinding>(
        LayoutInflater.from(parent.context),
        layoutId,
        parent,
        false
    )
    binding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
    return AppViewHolder(binding)
}