package com.manta.kurly_work.network

import com.manta.kurly_work.model.Product
import com.manta.kurly_work.model.Section
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KurlyService {

    @GET("sections")
    suspend fun fetchSections(
        @Query("page") page: Int,
    ): List<Section>

    @GET("section/products")
    suspend fun fetchProducts(
        @Query("sectionId") sectionId: Int
    ): List<Product>
}