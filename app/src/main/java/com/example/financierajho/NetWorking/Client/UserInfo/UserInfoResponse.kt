package com.example.financierajho.NetWorking.Client.UserInfo

data class UserInfoResponse (
    val cliente: Cliente
)

data class Cliente (
    val id: Long,
    val dni: String,
    val nombres: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val telefono: String,
    val email: String,
    val direccion: String,
    val distrito: String,
    val departamento: String,
    val fechaRegistro: String
)
