package com.example.financierajho.Login

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
import com.example.financierajho.Home.HomeActivity
import com.example.financierajho.NetWorking.Login.APIService
import com.example.financierajho.NetWorking.Login.LoginRequestBody
import com.example.financierajho.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


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
        val prefs = requireActivity().getSharedPreferences("personal_data", Context.MODE_PRIVATE)
        val name = prefs.getString("NAME", "").toString()
        binding.dniText.text = if (name.isEmpty()) args.document else name
        binding.cambiarUserBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.loginBtn.setOnClickListener {
            val validate = PassWordValidator.validate(binding.claveEditText.text.toString())
            if (validate.isValid) {
                login()
            } else {
                Toast.makeText(requireContext(), validate.message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://appmobile.tech/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(APIService::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val request = LoginRequestBody(args.document, binding.claveEditText.text.toString())
            val response = withContext(Dispatchers.IO) {
                api.login(request)
            }
                val prefs = requireActivity().getSharedPreferences("personal_data", Context.MODE_PRIVATE)
                prefs.edit().putString("NAME",response.usuario.nombres).apply()
                prefs.edit().putString("DOCUMENT",args.document).apply()
                val intent = Intent(requireActivity(), HomeActivity::class.java)
                requireActivity().startActivity(intent)
                requireActivity().finish()

            } catch(e: Exception) {
                Toast.makeText(requireContext(), "Falló servicio",Toast.LENGTH_SHORT).show()
            }
        }

    }

}