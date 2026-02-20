package com.example.financierajho.Validators

import org.w3c.dom.Document

data class ValidatorData(val isValid: Boolean,val message: String?)

object LoginValidator {

    fun validateDocument(document: String): ValidatorData {

        if (document.isEmpty()) {
            return  ValidatorData(false,"Documento no debe estar vacío")
        }

        if (!(document.count() == 8 || document.count() == 10)) {
            return  ValidatorData(false,"Documento debe tener 8 0 10 digitos")
        }

        //Valide numeros


        return ValidatorData(true,null)
    }

    fun validatePassword(pass: String): ValidatorData {
        if (pass.isEmpty()) {
            return  ValidatorData(false,"Contreña no debe estar vacío")
        }

        if (pass.count() != 6) {
            return ValidatorData(false,"Contreña debe tener 8 0 10 digitos")
        }

        return ValidatorData(true,null)
    }


}