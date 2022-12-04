package com.manta.kurly_work.model

import androidx.recyclerview.widget.DiffUtil
import com.manta.kurly_work.BR
import com.manta.kurly_work.data.remote.entity.Product


data class ProductUiModel(
    val product: Product,
    val viewType: ViewType,
    var isSelected: Boolean = false
) : BaseModel<ProductUiModel> {

    enum class ViewType {
        Normal, Stretch
    }

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

    override val bindingVariableId: Int
        get() = BR.productModel
}

fun SectionUiModel.ViewType.toProductViewType(): ProductUiModel.ViewType = when (this) {
    SectionUiModel.ViewType.Grid,
    SectionUiModel.ViewType.Horizontal -> ProductUiModel.ViewType.Normal
    else -> ProductUiModel.ViewType.Stretch
}