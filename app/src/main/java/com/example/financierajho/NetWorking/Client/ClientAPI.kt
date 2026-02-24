package com.example.financierajho.NetWorking.Client

import com.example.financierajho.NetWorking.Client.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ClientAPI {
    @GET("api/cliente/productos")
    @Headers("Content-Type: application/json")
    suspend fun getProducts(
        @Header("Authorization") token: String
    ): ProductsResponse

}