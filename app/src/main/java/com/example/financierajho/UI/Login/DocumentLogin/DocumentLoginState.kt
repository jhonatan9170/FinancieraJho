package com.example.financierajho.UI.Login.DocumentLogin

sealed class DocumentLoginState {

    object Idle: DocumentLoginState()

    data class NavigateLogin(val document: String): DocumentLoginState()

    data class Error(val message: String): DocumentLoginState()
}
