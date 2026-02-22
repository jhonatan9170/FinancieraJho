package com.example.financierajho.UI.Home.Products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financierajho.Data.Sesion
import com.example.financierajho.NetWorking.APIService
import com.example.financierajho.NetWorking.Login.LoginRequestBody
import com.example.financierajho.R
import com.example.financierajho.UI.Home.HomeActivity
import com.example.financierajho.databinding.FragmentProductsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class ProductsFragment : Fragment() {
    var _binding: FragmentProductsBinding? = null
    val binding get() = _binding!!

    private  lateinit var adapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ProductsAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        getProducts()

    }

    private fun getProducts() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://appmobile.tech/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            binding.loaderContainer.visibility = View.VISIBLE
            try {

                val autorization = " Bearer " + Sesion.token
                val response = withContext(Dispatchers.IO) {
                    api.getProducts(autorization)
                }
                binding.loaderContainer.visibility = View.GONE
                val products = response.productos.map {
                    ProductModel(it)
                }
                adapter.updateList(products)

            } catch(e: Exception) {
                binding.loaderContainer.visibility = View.GONE
                Toast.makeText(requireContext(), "Falló servicio", Toast.LENGTH_SHORT).show()
            }
        }
    }

}