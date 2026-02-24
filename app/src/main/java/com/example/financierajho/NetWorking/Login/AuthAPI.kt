package com.example.financierajho.NetWorking.Login
import com.example.financierajho.Data.Sesion
import com.example.financierajho.NetWorking.Login.LoginRequestBody
import com.example.financierajho.NetWorking.Login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthAPI {
    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequestBody
    ): Response<LoginResponse>

    @POST("api/auth/logout")
    suspend fun logout(
        @Header("Authorization") token: String = Sesion.token
    ): Response<LogoutResponse>
}