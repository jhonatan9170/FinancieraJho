package com.example.financierajho.NetWorking.Client

import com.example.financierajho.Data.Sesion
import com.example.financierajho.NetWorking.Client.Products.ProductsResponse
import com.example.financierajho.NetWorking.Client.UserInfo.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ClientAPI {
    @GET("api/cliente/productos")
    @Headers("Content-Type: application/json")
    suspend fun getProducts(
        @Header("Authorization") token: String = Sesion.token
    ): ProductsResponse

    @GET("api/cliente/perfil")
    @Headers("Content-Type: application/json")
    suspend fun getInfoUser(
        @Header("Authorization") token: String = Sesion.token
    ): UserInfoResponse

}