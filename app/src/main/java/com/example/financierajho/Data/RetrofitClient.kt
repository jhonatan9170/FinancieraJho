package com.example.financierajho.Data
import com.example.financierajho.NetWorking.Client.ClientAPI
import com.example.financierajho.NetWorking.Login.AuthAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://appmobile.tech/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authApi = retrofit.create(AuthAPI::class.java)
    val clientApi = retrofit.create(ClientAPI::class.java)
}