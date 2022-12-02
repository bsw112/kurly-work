package com.manta.kurly_work.network

import com.manta.kurly_work.network.data.Product
import com.manta.kurly_work.network.data.Section
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val kurlyService: KurlyService
) {
    suspend fun fetchSections(page: Int): List<Section> = kurlyService.fetchSections(page).data
    suspend fun fetchProducts(sectionId: Int): List<Product> = kurlyService.fetchProducts(sectionId).data
}