package com.example.financierajho.UI.Home.Products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financierajho.Data.RetrofitClient
import com.example.financierajho.Data.Sesion
import com.example.financierajho.databinding.FragmentProductsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewLifecycleOwner.lifecycleScope.launch {
            binding.loaderContainer.visibility = View.VISIBLE
            try {

                val autorization = " Bearer " + Sesion.token
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.clientApi.getProducts(autorization)
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