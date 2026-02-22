package com.example.financierajho.UI.Home.Products

import com.example.financierajho.NetWorking.Client.Producto

data class ProductModel(val id: Int, val name: String, val balance: String,val isActive: Boolean){

    constructor(productResponse: Producto): this(
        id = productResponse.productoID,
        name = productResponse.nombre,
        balance = productResponse.saldo.toString(),
        isActive = productResponse.tieneProducto == 1
    )

}
