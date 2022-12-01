package com.manta.kurly_work.model


enum class ViewType(val type: String) {
    Vertical("vertical"), Horizontal("horizontal"), Grid("grid");

    companion object {
        fun fromString(type: String): ViewType? = values().find { it.type == type }
    }
}

data class SectionUiModel(
    val sectionTitle: String,
    val products: List<Product>,
    val viewType: ViewType
)