package com.example.financierajho.NetWorking.Login

import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequestBody
    ): LoginResponse

}