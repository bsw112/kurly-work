package com.manta.kurly_work.data.remote

import com.manta.kurly_work.data.remote.entity.Product
import com.manta.kurly_work.data.remote.entity.Section
import com.manta.kurly_work.model.BaseResponse
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