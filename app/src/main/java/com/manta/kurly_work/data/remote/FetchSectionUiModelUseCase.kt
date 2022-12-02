package com.manta.kurly_work.data.remote

import com.manta.kurly_work.data.local.AppPreference
import com.manta.kurly_work.data.remote.entity.Section
import com.manta.kurly_work.model.ProductUiModel
import com.manta.kurly_work.model.SectionUiModel
import com.manta.kurly_work.model.toProductViewType
import javax.inject.Inject

class FetchSectionUiModelUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val preference: AppPreference
) {
    suspend fun fetchSectionUiModel(page: Int): List<SectionUiModel> {
        val sections: List<Section> = mainRepository.fetchSections(page)
        val favoriteProductIdList: Set<String> = preference.favoriteProductIdList
        return sections.map { section ->
            val products = mainRepository.fetchProducts(section.id)
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
                        isSelected = favoriteProductIdList.contains(product.id.toString()),
                        onClickFavorite = { productId, isSelected ->
                            if(isSelected){
                                preference.addFavoriteProduct(productId)
                            }else{
                                preference.removeFavoriteProduct(productId)
                            }
                        }
                    )
                }
            )
        }
    }
}