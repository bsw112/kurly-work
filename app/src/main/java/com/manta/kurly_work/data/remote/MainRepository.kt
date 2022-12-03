package com.manta.kurly_work.data.remote

import com.manta.kurly_work.data.remote.entity.Product
import com.manta.kurly_work.data.remote.entity.Section
import com.manta.kurly_work.model.BaseResponse
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val kurlyService: KurlyService
) {
    suspend fun fetchSections(page: Int): BaseResponse<Section> = kurlyService.fetchSections(page)
    suspend fun fetchProducts(sectionId: Int): BaseResponse<Product> = kurlyService.fetchProducts(sectionId)
}