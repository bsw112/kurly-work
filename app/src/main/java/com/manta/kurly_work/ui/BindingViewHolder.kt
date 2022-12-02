package com.manta.kurly_work.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(bindingId: Int, item: Any) {
        binding.setVariable(bindingId, item)
        binding.executePendingBindings()
    }

    inline fun <reified VH : ViewDataBinding> applyBinding(block: VH.() -> Unit): BindingViewHolder {
        if (binding is VH) {
            block(binding)
        }
        return this
    }
}

fun createBindingViewHolder(
    @LayoutRes layoutId: Int,
    parent: ViewGroup
): BindingViewHolder {
    val binding = DataBindingUtil.inflate<ViewDataBinding>(
        LayoutInflater.from(parent.context),
        layoutId,
        parent,
        false
    )
    binding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
    return BindingViewHolder(binding)
}