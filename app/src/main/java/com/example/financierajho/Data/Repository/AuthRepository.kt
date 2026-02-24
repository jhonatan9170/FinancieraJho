package com.example.financierajho.Data.Repository

import com.example.financierajho.Data.RetrofitClient
import com.example.financierajho.NetWorking.Login.LoginRequestBody
import com.example.financierajho.UI.Login.Login.LoginModel

class AuthRepository {

    suspend fun login(dni: String, pass: String): LoginModel {

        return try {
            val request = LoginRequestBody(dni, pass)
            val response = RetrofitClient.authApi.login(request)
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                LoginModel(body.usuario.nombres, body.usuario.dni, body.token, true, null)
            } else {
                LoginModel("Error de data")
            }

        } catch (e: Exception){
            LoginModel("Error de conexión")
        }

    }

    suspend fun logout(): String? {
        return try {
            val response = RetrofitClient.authApi.logout()
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.mensaje
            } else {
                null
            }

        } catch (e: Exception){
            null
        }
    }

}