package com.example.financierajho.UI.Home.Products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financierajho.R
import com.example.financierajho.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {
    var _binding: FragmentProductsBinding? = null
    val binding get() = _binding!!
    private val data = listOf<ProductModel>(
        ProductModel(1,"CTS","1200", isActive = true),
        ProductModel(2,"Ahorra mucho mas","1200", isActive = true),
        ProductModel(3,"JohPay!","1200", isActive = true)

    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = ProductsAdapter(data)

    }
}