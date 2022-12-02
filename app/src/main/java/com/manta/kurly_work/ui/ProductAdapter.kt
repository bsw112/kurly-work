package com.manta.kurly_work.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.manta.kurly_work.R
import com.manta.kurly_work.databinding.ItemProductNormalBinding
import com.manta.kurly_work.databinding.ItemProductStretchBinding
import com.manta.kurly_work.model.ProductUiModel

class ProductAdapter : ListAdapter<ProductUiModel, AppViewHolder>(createItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val layoutId = if (viewType == ProductUiModel.ViewType.Normal.ordinal) {
            R.layout.item_product_normal
        } else {
            R.layout.item_product_stretch
        }
        return createAppViewHolder(layoutId, parent)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item.bindingVariableId, item)

            val onFavoriteClick = { _: View ->
                item.isSelected = !item.isSelected
                item.onClickFavorite(item.product.id, item.isSelected)
                notifyItemChanged(holder.bindingAdapterPosition)
            }

            when (holder.binding) {
                is ItemProductNormalBinding -> holder.binding.imgFavorite.setOnClickListener(
                    onFavoriteClick
                )
                is ItemProductStretchBinding -> holder.binding.imgFavorite.setOnClickListener(
                    onFavoriteClick
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.viewType?.ordinal ?: ProductUiModel.ViewType.Normal.ordinal
    }
}