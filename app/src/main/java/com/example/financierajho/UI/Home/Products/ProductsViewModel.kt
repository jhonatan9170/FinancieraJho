package com.example.financierajho.UI.Home.Products
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.financierajho.Data.Repository.ClientRepository
import kotlinx.coroutines.launch

class ProductsViewModel(
    val clientRepository: ClientRepository
): ViewModel()  {
    private val _state = MutableLiveData<ProductsState>(ProductsState.Idle)
    val state: LiveData<ProductsState> = _state

    fun getProducts(){
        _state.value = ProductsState.Loading
        viewModelScope.launch {
            val products = clientRepository.getProducts()
            if (products != null) {
                _state.value = ProductsState.Succes(products)
            } else {
                _state.value = ProductsState.Error("Falló el servicio")
            }
        }
    }

}
class ProductsViewModelFactory(
    val clientRepository: ClientRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsViewModel(clientRepository) as T
    }
}