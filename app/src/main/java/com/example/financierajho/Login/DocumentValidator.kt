package com.example.financierajho.Login

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