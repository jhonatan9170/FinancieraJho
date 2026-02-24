package com.example.financierajho.Data

object Sesion {
    var token: String = ""
        get() {return if (field.isNotBlank()) "Bearer $field" else ""}
        set(value){field = value}

}