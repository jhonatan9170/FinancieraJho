package com.example.financierajho.NetWorking.Client.Products

data class ProductsResponse (
    val productos: List<Producto>
)

data class Producto (
    val productoID: Int,
    val codigo: String,
    val nombre: String,
    val descripcion: String,
    val permiteTransferir: Long,
    val cuentaID: Long? = null,
    val numeroCuenta: String? = null,
    val saldo: Double? = null,
    val moneda: String? = null,
    val estado: String? = null,
    val tieneProducto: Int
)
