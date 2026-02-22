package com.example.financierajho.UI.Login

data class ValidateDocument (val isValid: Boolean , val message: String?)
object DocumentValidator {

    fun validate(document: String): ValidateDocument {
        if (document.isEmpty()) {
            return ValidateDocument(false,"El dni no debe estar vacío")
        }
        if (document.count() != 8) {
            return ValidateDocument(false,"El dni tiene que tener 8 digitos")
        }
        return ValidateDocument(true, null)
    }

}

object PassWordValidator {
    fun validate(document: String): ValidateDocument {
        if (document.isEmpty()) {
            return ValidateDocument(false,"La contraseña no debe estar vacío")
        }
        if (document.count() != 6) {
            return ValidateDocument(false,"La contraseña  tiene que tener 6 digitos")
        }
        return ValidateDocument(true, null)
    }

}