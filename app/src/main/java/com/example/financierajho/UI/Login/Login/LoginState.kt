package com.example.financierajho.UI.Login.Login

sealed class LoginState{
    data class Idle(val title: String): LoginState()

    object Success : LoginState()

    data class Error(val message: String): LoginState()

    object Loading: LoginState()

    object BackNavigate: LoginState()
}