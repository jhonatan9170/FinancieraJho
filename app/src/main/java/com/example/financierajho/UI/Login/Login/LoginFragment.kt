package com.example.financierajho.UI.Login.Login

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
import com.example.financierajho.UI.Home.HomeActivity
import com.example.financierajho.NetWorking.Login.LoginRequestBody
import com.example.financierajho.Data.PassWordValidator
import com.example.financierajho.Data.Repository.AuthRepository
import com.example.financierajho.Data.Repository.UserLocalRepository
import com.example.financierajho.Data.RetrofitClient
import com.example.financierajho.Data.Sesion
import com.example.financierajho.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

//DRY : DONT REPEAT YOURSELF
class LoginFragment : Fragment() {

    var _binding: FragmentLoginBinding? = null
    val binding get() = _binding!!
    private val args: LoginFragmentArgs by navArgs()
    val userLocalRepository by lazy { UserLocalRepository(requireContext())}
    val authRepository = AuthRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = userLocalRepository.name
        binding.dniText.text = if (name.isEmpty()) args.document else name
        binding.cambiarUserBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.loginBtn.setOnClickListener {
            val validate = PassWordValidator.validate(binding.claveEditText.text.toString())
            if (validate.isValid) {
                login()
            } else {
                showToast(validate.message.toString())
            }
        }
    }

    private fun login() {
        viewLifecycleOwner.lifecycleScope.launch {
            val loginData = withContext(Dispatchers.IO) {
                authRepository.login(args.document, binding.claveEditText.text.toString())
            }
            if (loginData.succes) {
                userLocalRepository.name = loginData.name
                userLocalRepository.document = loginData.document
                Sesion.token = loginData.token
                goToHome()
            } else {
                showToast(loginData.message.toString())
            }
        }
    }

    private fun goToHome(){
        val intent = Intent(requireActivity(), HomeActivity::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message,Toast.LENGTH_SHORT).show()
    }

}