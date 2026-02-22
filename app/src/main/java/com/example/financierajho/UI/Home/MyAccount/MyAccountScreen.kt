package com.example.financierajho.UI.Home.MyAccount
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Persona(
    val nombre: String,
    val apellidos: String,
    val direccion: String,
    val fechaNacimiento: String,
    val telefono: String,
    val email: String
)

@Composable
fun MyAccountScreen(
    persona: Persona,
    onCerrarSesion: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoItem(label = "Nombre", value = persona.nombre)
                InfoItem(label = "Apellidos", value = persona.apellidos)
                InfoItem(label = "Dirección", value = persona.direccion)
                InfoItem(label = "Fecha de nacimiento", value = persona.fechaNacimiento)
                InfoItem(label = "Teléfono", value = persona.telefono)
                InfoItem(label = "Email", value = persona.email)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp),
                contentAlignment = Alignment.Center
            ) {
                ClickableText(
                    text = buildAnnotatedString {
                        pushStyle(
                            SpanStyle(
                                color = Color.Red,
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        append("Cerrar sesión")
                    },
                    onClick = { onCerrarSesion() }
                )
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Text(
            text = value,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}