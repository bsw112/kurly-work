package com.manta.kurly_work.ui

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.manta.kurly_work.BR
import com.manta.kurly_work.R
import com.manta.kurly_work.databinding.ItemSectionGridBinding
import com.manta.kurly_work.databinding.ItemSectionHorizontalBinding
import com.manta.kurly_work.databinding.ItemSectionVerticalBinding
import com.manta.kurly_work.model.ProductUiModel
import com.manta.kurly_work.model.SectionUiModel
import com.manta.kurly_work.model.SectionViewType

class MainPagingAdapter :
    PagingDataAdapter<SectionUiModel, AppViewHolder<SectionUiModel>>(SectionUIModelItemCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppViewHolder<SectionUiModel> {
        return when (viewType) {
            SectionViewType.Horizontal.ordinal -> createViewHolder<SectionUiModel>(
                R.layout.item_section_horizontal,
                BR.sectionModel,
                parent
            ).also {
                (it.binding as ItemSectionHorizontalBinding).rvProduct.adapter =
                    AppListAdapter<ProductUiModel>(
                        R.layout.item_product_horizontal,
                        BR.productModel
                    )
            }
            SectionViewType.Vertical.ordinal -> createViewHolder<SectionUiModel>(
                R.layout.item_section_vertical,
                BR.sectionModel,
                parent
            ).also {
                (it.binding as ItemSectionVerticalBinding).rvProduct.adapter =
                    AppListAdapter<ProductUiModel>(R.layout.item_product_vertical, BR.productModel)
            }
            SectionViewType.Grid.ordinal -> createViewHolder<SectionUiModel>(
                R.layout.item_section_grid,
                BR.sectionModel,
                parent
            ).also {
                (it.binding as ItemSectionGridBinding).rvProduct.adapter =
                    AppListAdapter<ProductUiModel>(
                        R.layout.item_section_horizontal,
                        BR.productModel
                    )
            }
            else -> createViewHolder<SectionUiModel>(
                R.layout.item_section_horizontal,
                BR.sectionModel,
                parent
            ).also {
                (it.binding as ItemSectionHorizontalBinding).rvProduct.adapter =
                    AppListAdapter<ProductUiModel>(
                        R.layout.item_product_horizontal,
                        BR.productModel
                    )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.viewType?.ordinal ?: 1
    }

    override fun onBindViewHolder(
        holder: AppViewHolder<SectionUiModel>,
        position: Int
    ) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}

object SectionUIModelItemCallback : DiffUtil.ItemCallback<SectionUiModel>() {
    override fun areItemsTheSame(oldItem: SectionUiModel, newItem: SectionUiModel): Boolean {
        return oldItem.sectionId == newItem.sectionId
    }

    override fun areContentsTheSame(oldItem: SectionUiModel, newItem: SectionUiModel): Boolean {
        return oldItem == newItem
    }
}
