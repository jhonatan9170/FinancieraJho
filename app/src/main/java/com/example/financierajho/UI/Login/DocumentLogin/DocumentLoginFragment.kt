package com.example.financierajho.UI.Login.DocumentLogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.financierajho.Data.Repository.UserLocalRepository
import com.example.financierajho.databinding.FragmentDocumentLoginBinding
// S : Single resposability
class DocumentLoginFragment : Fragment() {

    var _binding: FragmentDocumentLoginBinding? = null
    val binding get() = _binding!!

    private lateinit var viewModel: DocumentLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDocumentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localRepository = UserLocalRepository(requireContext())
        val factory = DocumentLoginViewModelFactory(localRepository)
        viewModel = ViewModelProvider(this, factory)[DocumentLoginViewModel::class.java]
        viewModel.validateDocumentSaved()
        binding.nextBtn.setOnClickListener {
            viewModel.validateDocument(binding.dniEditTxt.text.toString())
        }
        observedState()
    }

    private fun observedState() {
        viewModel.state.observe(viewLifecycleOwner) {state ->
            when(state) {
                is DocumentLoginState.Idle -> {
                    binding.dniEditTxt.setText("")
                }
                is DocumentLoginState.NavigateLogin -> {
                    val action = DocumentLoginFragmentDirections.actionDocumentLoginFragmentToLoginFragment(state.document)
                    findNavController().navigate(action)
                }
                is DocumentLoginState.Error -> {
                    Toast.makeText(requireContext(),state.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}