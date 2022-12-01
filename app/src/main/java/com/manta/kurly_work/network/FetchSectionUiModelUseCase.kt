package com.manta.kurly_work.network

import com.manta.kurly_work.model.ProductUiModel
import com.manta.kurly_work.model.SectionUiModel
import com.manta.kurly_work.model.SectionViewType
import javax.inject.Inject

class FetchSectionUiModelUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend fun fetchSectionUiModel(page: Int): List<SectionUiModel> {
        val sections = mainRepository.fetchSections(page)
        return sections.map { section ->
            val products = mainRepository.fetchProducts(section.id)
            SectionUiModel(
                sectionId = section.id,
                sectionTitle = section.title,
                products = products.map { ProductUiModel(it) },
                viewType = SectionViewType.fromString(section.type) ?: SectionViewType.Horizontal
            )
        }
    }
}