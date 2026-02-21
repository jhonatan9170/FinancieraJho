package com.example.financierajho.NetWorking.Login

data class LoginResponse (
    val mensaje: String,
    val token: String,
    val usuario: UserResponse
)

data class UserResponse (
    val id: Int,
    val dni: String,
    val nombres: String,
    val apellidos: String
)

