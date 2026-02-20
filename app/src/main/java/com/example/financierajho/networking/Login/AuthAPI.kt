package com.example.financierajho.networking.Login

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequestBody
    ): Response<LoginResponse>
}