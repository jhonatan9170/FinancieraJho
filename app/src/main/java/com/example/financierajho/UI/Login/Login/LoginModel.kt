package com.example.financierajho.UI.Login.Login

data class LoginModel(
    val name: String,
    val document: String,
    val token: String,
    val succes: Boolean,
    val message: String?
) {
    constructor(failMessage: String): this("","","",false, failMessage)
}

