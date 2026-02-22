package com.example.financierajho.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.financierajho.Home.HomeActivity
import com.example.financierajho.databinding.FragmentDocumentLoginBinding
// S : Single resposability
class DocumentLoginFragment : Fragment() {

    var _binding: FragmentDocumentLoginBinding? = null
    val binding get() = _binding!!

    private lateinit var navigation: LoginRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = requireActivity().getSharedPreferences("personal_data", Context.MODE_PRIVATE)
        val document = prefs.getString("DOCUMENT", "").toString()
        if (document.count() == 8) {
            val action = DocumentLoginFragmentDirections.actionDocumentLoginFragmentToLoginFragment(document)
            findNavController().navigate(action)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDocumentLoginBinding.inflate(inflater, container, false)
        navigation = LoginRouter(requireActivity())
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val document = binding.dniEditTxt.text.toString()
        val validator = DocumentValidator.validate(document)
        if (validator.isValid) {
            val action = DocumentLoginFragmentDirections.actionDocumentLoginFragmentToLoginFragment(document)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(),validator.message,Toast.LENGTH_LONG).show()
        }

    }

}