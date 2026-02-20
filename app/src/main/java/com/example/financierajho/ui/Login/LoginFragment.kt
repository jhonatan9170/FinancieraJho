package com.example.financierajho.ui.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.financierajho.ui.Home.HomeActivity
import com.example.financierajho.R
import com.example.financierajho.Validators.LoginValidator
import com.example.financierajho.Validators.ValidatorData
import com.example.financierajho.databinding.FragmentLoginBinding
import com.example.financierajho.databinding.FragmentLoginDocumentBinding
import com.example.financierajho.networking.Login.AuthAPI
import com.example.financierajho.networking.Login.LoginRequestBody
import com.example.financierajho.networking.Login.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {

    var _binding: FragmentLoginBinding? = null
    val binding get() = _binding!!

    private val args: LoginFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dniText.text = args.dni
        binding.cambiarUserBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.loginBtn.setOnClickListener {
            val pass = binding.claveEditText.text.toString()
            val validator = LoginValidator.validatePassword(pass)
            if (validator.isValid) {
                login()
            } else {
                Toast.makeText(requireContext(), validator.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun login(){

        val requestBody = LoginRequestBody(args.dni, binding.claveEditText.toString())
        viewLifecycleOwner.lifecycleScope.launch {
            try {

                val response = withContext(Dispatchers.IO)  {
                    RetrofitClient.api.login(requestBody)
                }
                if (response.isSuccessful) {
                    response.body()?.let {
                        goToNextActivity(it.token)
                    } ?: Toast.makeText(requireContext(), "Network Fail response des", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Network Fail response", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Network Fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToNextActivity(token: String){
        val prefs = requireContext().getSharedPreferences("personal_data", Context.MODE_PRIVATE)
        prefs.edit().putString("TOKEN",token).apply()
        val intent = Intent(requireActivity(), HomeActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}