package com.manta.kurly_work.network

import com.manta.kurly_work.model.BaseResponse
import com.manta.kurly_work.network.data.Product
import com.manta.kurly_work.network.data.Section
import retrofit2.http.GET
import retrofit2.http.Query

interface KurlyService {

    @GET("sections")
    suspend fun fetchSections(
        @Query("page") page: Int,
    ): BaseResponse<Section>

    @GET("section/products")
    suspend fun fetchProducts(
        @Query("sectionId") sectionId: Int
    ): BaseResponse<Product>
}