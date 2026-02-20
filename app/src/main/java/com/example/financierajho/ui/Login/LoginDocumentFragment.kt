package com.example.financierajho.ui.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.financierajho.ui.Home.HomeActivity
import com.example.financierajho.R
import com.example.financierajho.Validators.LoginValidator
import com.example.financierajho.databinding.FragmentLoginDocumentBinding
import com.example.financierajho.networking.Login.LoginRequestBody
import org.w3c.dom.Document

// SOLID // S: Single Resposability
class LoginDocumentFragment : Fragment() {

    var _binding: FragmentLoginDocumentBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginDocumentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextBtn.setOnClickListener {
            val document = binding.dniEditTxt.text.toString()
            val validator = LoginValidator.validateDocument(document)
            if (validator.isValid ) {
                val action = LoginDocumentFragmentDirections.actionLoginDocumentFragmentToLoginFragment(
                    document
                )
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(),validator.message.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }



}