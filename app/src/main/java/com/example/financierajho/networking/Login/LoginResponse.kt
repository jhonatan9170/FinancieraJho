package com.example.financierajho.networking.Login

data class LoginResponse (
    val mensaje: String,
    val token: String,
    val usuario: Usuario
)

data class Usuario (
    val id: Int,
    val dni: String,
    val nombres: String,
    val apellidos: String
)

