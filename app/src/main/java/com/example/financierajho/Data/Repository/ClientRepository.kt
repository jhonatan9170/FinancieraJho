package com.example.financierajho.Data.Repository
import com.example.financierajho.Data.RetrofitClient
import com.example.financierajho.UI.Home.MyAccount.Persona
import com.example.financierajho.UI.Home.Products.ProductModel

class ClientRepository {

    suspend fun getProducts(): List<ProductModel>? {
        return try {
            val response = RetrofitClient.clientApi.getProducts()
            response.productos.map { ProductModel(it) }
        } catch (e: Exception){
            null
        }
    }

    suspend fun getUserInfo(): Persona? {
        return try {
            val response = RetrofitClient.clientApi.getInfoUser()
            Persona(
                nombre = response.cliente.nombres,
                apellidos = response.cliente.apellidos,
                direccion = "${response.cliente.direccion}, ${response.cliente.distrito}",
                fechaNacimiento = response.cliente.fechaNacimiento,
                telefono = response.cliente.telefono,
                email = response.cliente.email
            )
        } catch (e: Exception){
            null
        }
    }

}