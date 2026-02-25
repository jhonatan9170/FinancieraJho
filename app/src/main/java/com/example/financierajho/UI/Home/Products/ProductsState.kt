package com.example.financierajho.UI.Home.Products

sealed class ProductsState {

    object Idle: ProductsState()
    data class Succes(val products: List<ProductModel>): ProductsState()
    data class Error(val meesage: String): ProductsState()
    object Loading: ProductsState()
}
