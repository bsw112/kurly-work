package com.manta.kurly_work.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.manta.kurly_work.model.BaseModel

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

open class AppListAdapter<T : BaseModel<T>>(
    private val layoutId: Int
) : ListAdapter<T, BindingViewHolder>(createItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return createBindingViewHolder(layoutId, parent)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it.bindingVariableId, it)

        }
    }
}