package com.example.financierajho.networking.Login

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: AuthAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://appmobile.tech")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthAPI::class.java)
    }
}