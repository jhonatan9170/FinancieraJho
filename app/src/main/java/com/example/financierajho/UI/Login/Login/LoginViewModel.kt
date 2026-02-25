package com.example.financierajho.UI.Login.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.financierajho.Data.PassWordValidator
import com.example.financierajho.Data.Repository.AuthRepository
import com.example.financierajho.Data.Repository.UserLocalRepository
import com.example.financierajho.Data.Sesion
import kotlinx.coroutines.launch

class LoginViewModel(
    val userLocalRepository: UserLocalRepository,
    val authRepository: AuthRepository
): ViewModel() {
    private val _state = MutableLiveData<LoginState>(LoginState.Idle(""))
    val state: LiveData<LoginState> = _state

    fun checkTitleLogin(document: String) {
        val name = userLocalRepository.name
        val title = if (name.isEmpty()) document else name
        _state.value = LoginState.Idle (title)
    }

    fun changeUser() {
        _state.value = LoginState.BackNavigate
    }

    fun login(document: String, pass:String) {
        val validate = PassWordValidator.validate(pass)
        if (!validate.isValid){
            _state.value = LoginState.Error(validate.message.toString())
            return
        }
        _state.value = LoginState.Loading
        viewModelScope.launch {
            val loginData = authRepository.login(document, pass)
            if (loginData.succes){
                userLocalRepository.name = loginData.name
                userLocalRepository.document = loginData.document
                Sesion.token = loginData.token
                _state.value = LoginState.Success
            } else {
                _state.value = LoginState.Error(loginData.message.toString())
            }
        }
    }

}

class LoginViewModelFactory(
    val userLocalRepository: UserLocalRepository,
    val authRepository: AuthRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(userLocalRepository, authRepository) as T
    }
}
