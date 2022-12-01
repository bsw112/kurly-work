package com.manta.kurly_work.network

import com.manta.kurly_work.model.Product
import com.manta.kurly_work.model.Section
import retrofit2.HttpException
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val kurlyService: KurlyService
) {
    suspend fun fetchSections(page: Int, onError: (Throwable) -> Unit): List<Section> {
        val response = kurlyService.fetchSections(page)
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            body
        } else {
            onError(HttpException(response))
            emptyList()
        }
    }

    suspend fun fetchProducts(sectionId: Int, onError: (Throwable) -> Unit): List<Product> {
        val response = kurlyService.fetchProducts(sectionId)
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            body
        } else {
            onError(HttpException(response))
            emptyList()
        }
    }
}