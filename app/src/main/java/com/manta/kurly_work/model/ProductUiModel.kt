package com.manta.kurly_work.model

import androidx.recyclerview.widget.DiffUtil

data class ProductUiModel(
    val product: Product,
    val isSelected: Boolean = false
) : BaseModel<ProductUiModel> {
    override val itemCallback: DiffUtil.ItemCallback<ProductUiModel> =
        object : DiffUtil.ItemCallback<ProductUiModel>() {
            override fun areItemsTheSame(
                oldItem: ProductUiModel,
                newItem: ProductUiModel
            ): Boolean {
                return oldItem.product.id == newItem.product.id
            }

            override fun areContentsTheSame(
                oldItem: ProductUiModel,
                newItem: ProductUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
}