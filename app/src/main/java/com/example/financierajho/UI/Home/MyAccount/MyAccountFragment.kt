package com.example.financierajho.UI.Home.MyAccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView

class MyAccountFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        ComposeView(requireContext()).apply {
            val personaEjemplo = Persona(
                nombre = "Anthony",
                apellidos = "García Pérez",
                direccion = "Av. Siempre Viva 742",
                fechaNacimiento = "01/01/1990",
                telefono = "+51 987654321",
                email = "anthony@email.com"
            )
            setContent {
                MyAccountScreen(
                    persona = personaEjemplo,
                    {}

                )
            }
        }
}