package com.example.financierajho.UI.Home.Products
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financierajho.Data.Repository.ClientRepository
import com.example.financierajho.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {
    var _binding: FragmentProductsBinding? = null
    val binding get() = _binding!!

    private  lateinit var adapter: ProductsAdapter

    private lateinit var viewModel: ProductsViewModel
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
        val clientRepository = ClientRepository()
        val factory = ProductsViewModelFactory(clientRepository)
        viewModel = ViewModelProvider(this, factory)[ProductsViewModel::class.java]
        viewModel.getProducts()
        observedState()
    }

    private fun observedState() {
        viewModel.state.observe(viewLifecycleOwner) {state ->
            binding.loaderContainer.visibility = if(state == ProductsState.Loading) View.VISIBLE else View.GONE
            when(state) {
                is ProductsState.Error -> {
                    Toast.makeText(requireContext(), state.meesage, Toast.LENGTH_SHORT).show()
                }
                is ProductsState.Succes -> {
                    adapter.updateList(state.products)
                }
                else -> { Unit}
            }
        }
    }

}