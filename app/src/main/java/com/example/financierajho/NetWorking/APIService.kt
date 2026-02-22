package com.example.financierajho.NetWorking

import com.example.financierajho.NetWorking.Client.ProductsResponse
import com.example.financierajho.NetWorking.Login.LoginRequestBody
import com.example.financierajho.NetWorking.Login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {
    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequestBody
    ): LoginResponse

    @GET("api/cliente/productos")
    @Headers("Content-Type: application/json")
    suspend fun getProducts(
        @Header("Authorization") token: String
    ): ProductsResponse

}