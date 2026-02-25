package com.example.financierajho.UI.Login.DocumentLogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.financierajho.Data.DocumentValidator
import com.example.financierajho.Data.Repository.UserLocalRepository

class DocumentLoginViewModel(val userLocalRepository: UserLocalRepository): ViewModel() {

    private val _state = MutableLiveData<DocumentLoginState>(DocumentLoginState.Idle)
    val state: LiveData<DocumentLoginState>  = _state
    var wasDocumentChecked = false

    fun validateDocumentSaved() {
        if (!wasDocumentChecked) {
            wasDocumentChecked = true
            val document = userLocalRepository.document
            if (document.count() == 8 ){
                _state.value = DocumentLoginState.NavigateLogin(document)
            } else {
                _state.value = DocumentLoginState.Idle
            }
        } else {
            _state.value = DocumentLoginState.Idle
        }
    }

    fun validateDocument(document: String) {
        val validator = DocumentValidator.validate(document)
        if (validator.isValid) {
            _state.value = DocumentLoginState.NavigateLogin(document)
        } else {
            _state.value = DocumentLoginState.Error(validator.message.toString())
        }
    }

}

class DocumentLoginViewModelFactory(val userLocalRepository: UserLocalRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DocumentLoginViewModel(userLocalRepository) as T
    }
}
