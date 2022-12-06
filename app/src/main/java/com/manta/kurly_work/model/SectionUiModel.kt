package com.manta.kurly_work.model

import androidx.recyclerview.widget.DiffUtil
import com.manta.kurly_work.BR

data class SectionUiModel(
    val sectionId: Int,
    val sectionTitle: String,
    val products: List<ProductUiModel>,
    val viewType: ViewType
) : BaseModel<SectionUiModel> {

    enum class ViewType(val type: String) {
        Vertical("vertical"), Horizontal("horizontal"), Grid("grid");

        companion object {
            fun fromString(type: String): ViewType? = values().find { it.type == type }
        }
    }

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

    override val bindingVariableId: Int
        get() = BR.sectionModel
}