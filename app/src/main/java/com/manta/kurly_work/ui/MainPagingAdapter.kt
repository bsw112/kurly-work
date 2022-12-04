package com.manta.kurly_work.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.manta.kurly_work.R
import com.manta.kurly_work.createItemCallback
import com.manta.kurly_work.databinding.ItemSectionGridBinding
import com.manta.kurly_work.databinding.ItemSectionHorizontalBinding
import com.manta.kurly_work.databinding.ItemSectionVerticalBinding
import com.manta.kurly_work.model.SectionUiModel

interface ProductUiEventListener {
    fun onClickFavorite(productId: Int)
}

class MainPagingAdapter(
  private val onClickFavorite: (productId: Int, isSelected: Boolean) -> Unit
) : PagingDataAdapter<SectionUiModel, BindingViewHolder>(createItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder {
        return when (viewType) {
            SectionUiModel.ViewType.Horizontal.ordinal -> createBindingViewHolder(
                R.layout.item_section_horizontal,
                parent
            ).applyBinding<ItemSectionHorizontalBinding> {
                rvProduct.adapter = ProductAdapter(onClickFavorite)
            }
            SectionUiModel.ViewType.Vertical.ordinal -> createBindingViewHolder(
                R.layout.item_section_vertical,
                parent
            ).applyBinding<ItemSectionVerticalBinding> {
                rvProduct.adapter = ProductAdapter(onClickFavorite)
            }
            SectionUiModel.ViewType.Grid.ordinal -> createBindingViewHolder(
                R.layout.item_section_grid,
                parent
            ).applyBinding<ItemSectionGridBinding> {
                rvProduct.layoutManager =
                    GridLayoutManager(parent.context, 2, GridLayoutManager.HORIZONTAL, false)
                rvProduct.adapter = ProductAdapter(onClickFavorite)
            }
            else -> createBindingViewHolder(
                R.layout.item_section_horizontal,
                parent
            ).applyBinding<ItemSectionHorizontalBinding> {
                rvProduct.adapter = ProductAdapter(onClickFavorite)
            }
        }
    }

    override fun onBindViewHolder(
        holder: BindingViewHolder,
        position: Int
    ) {
        getItem(position)?.let { uiModel ->
            holder.bind(uiModel.bindingVariableId, uiModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.viewType?.ordinal ?: 1
    }
}
