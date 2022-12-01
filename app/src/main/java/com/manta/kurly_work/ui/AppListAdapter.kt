package com.manta.kurly_work.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.manta.kurly_work.model.BaseModel

fun <T : BaseModel<T>> createDefaultItemCallback(): DiffUtil.ItemCallback<T> {
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
    private val layoutId: Int,
    private val bindingVariableId: Int,
) : ListAdapter<T, AppViewHolder<T>>(createDefaultItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder<T> {
        return createViewHolder(layoutId, bindingVariableId, parent)
    }

    override fun onBindViewHolder(holder: AppViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }
}