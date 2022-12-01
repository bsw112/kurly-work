package com.manta.kurly_work.network

import com.manta.kurly_work.model.SectionUiModel
import com.manta.kurly_work.model.ViewType
import javax.inject.Inject

class FetchSectionUiModelUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend fun fetchSectionUiModel(page: Int): List<SectionUiModel> {
        val sections = mainRepository.fetchSections(page)
        return sections.map { section ->
            val products = mainRepository.fetchProducts(section.id)
            SectionUiModel(
                section.title,
                products,
                ViewType.fromString(section.type) ?: ViewType.Horizontal
            )
        }
    }
}