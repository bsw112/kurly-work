package com.manta.kurly_work.model

import androidx.recyclerview.widget.DiffUtil


enum class SectionViewType(val type: String) {
    Vertical("vertical"), Horizontal("horizontal"), Grid("grid");

    companion object {
        fun fromString(type: String): SectionViewType? = values().find { it.type == type }
    }
}

data class SectionUiModel(
    val sectionId: Int,
    val sectionTitle: String,
    val products: List<ProductUiModel>,
    val viewType: SectionViewType
) : BaseModel<SectionUiModel> {
    override val itemCallback: DiffUtil.ItemCallback<SectionUiModel>
        get() = object : DiffUtil.ItemCallback<SectionUiModel>() {
            override fun areItemsTheSame(
                oldItem: SectionUiModel,
                newItem: SectionUiModel
            ): Boolean {
                return oldItem.sectionId == newItem.sectionId
            }

            override fun areContentsTheSame(
                oldItem: SectionUiModel,
                newItem: SectionUiModel
            ): Boolean {
                return oldItem == newItem
            }

        }
}