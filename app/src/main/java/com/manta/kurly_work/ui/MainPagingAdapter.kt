package com.manta.kurly_work.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.manta.kurly_work.R
import com.manta.kurly_work.databinding.ItemSectionGridBinding
import com.manta.kurly_work.databinding.ItemSectionHorizontalBinding
import com.manta.kurly_work.databinding.ItemSectionVerticalBinding
import com.manta.kurly_work.model.ProductUiModel
import com.manta.kurly_work.model.SectionUiModel
import com.manta.kurly_work.model.SectionViewType

class MainPagingAdapter : PagingDataAdapter<SectionUiModel, AppViewHolder>(createItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppViewHolder {
        return when (viewType) {
            SectionViewType.Horizontal.ordinal -> createAppViewHolder(
                R.layout.item_section_horizontal,
                parent
            ).applyBinding<ItemSectionHorizontalBinding> {
                rvProduct.adapter = AppListAdapter<ProductUiModel>(R.layout.item_product_horizontal)
            }
            SectionViewType.Vertical.ordinal -> createAppViewHolder(
                R.layout.item_section_vertical,
                parent
            ).applyBinding<ItemSectionVerticalBinding> {
                rvProduct.adapter = AppListAdapter<ProductUiModel>(R.layout.item_product_vertical)
            }
            SectionViewType.Grid.ordinal -> createAppViewHolder(
                R.layout.item_section_grid,
                parent
            ).applyBinding<ItemSectionGridBinding> {
                rvProduct.layoutManager = GridLayoutManager(parent.context, 2, GridLayoutManager.HORIZONTAL, false)
                rvProduct.adapter = AppListAdapter<ProductUiModel>(R.layout.item_product_horizontal)
            }
            else -> createAppViewHolder(
                R.layout.item_section_horizontal,
                parent
            ).applyBinding<ItemSectionHorizontalBinding> {
                rvProduct.adapter = AppListAdapter<ProductUiModel>(R.layout.item_product_horizontal)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.viewType?.ordinal ?: 1
    }

    override fun onBindViewHolder(
        holder: AppViewHolder,
        position: Int
    ) {
        getItem(position)?.let {
            holder.bind(it, it.bindingVariableId)
        }
    }
}
