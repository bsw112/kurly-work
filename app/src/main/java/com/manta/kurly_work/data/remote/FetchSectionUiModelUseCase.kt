package com.manta.kurly_work.data.remote

import com.manta.kurly_work.data.local.AppPreference
import com.manta.kurly_work.data.remote.entity.Section
import com.manta.kurly_work.model.*
import javax.inject.Inject

class FetchSectionUiModelUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val preference: AppPreference
) {
    suspend fun fetchSectionUiModel(page: Int): PagingUiModel<SectionUiModel> {
        val sectionResponse: BaseResponse<Section> = mainRepository.fetchSections(page)
        val favoriteProductIdList: Set<String> = preference.favoriteProductIdList
        val sectionUiModels = sectionResponse.data.map { section ->
            val products = mainRepository.fetchProducts(section.id).data
            val sectionViewType = SectionUiModel.ViewType.fromString(section.type)
                ?: SectionUiModel.ViewType.Horizontal
            SectionUiModel(
                sectionId = section.id,
                sectionTitle = section.title,
                viewType = sectionViewType,
                products = products.map { product ->
                    ProductUiModel(
                        product = product,
                        viewType = sectionViewType.toProductViewType(),
                        isSelected = favoriteProductIdList.contains(product.id.toString())
                    )
                }
            )
        }
        return PagingUiModel(
            nextPage = sectionResponse.page?.nextPage,
            sectionUiModel = sectionUiModels
        )
    }
}