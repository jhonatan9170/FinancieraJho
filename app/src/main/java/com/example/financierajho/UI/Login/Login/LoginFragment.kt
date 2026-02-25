package com.example.financierajho.UI.Login.Login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.financierajho.UI.Home.HomeActivity
import com.example.financierajho.Data.Repository.AuthRepository
import com.example.financierajho.Data.Repository.UserLocalRepository
import com.example.financierajho.databinding.FragmentLoginBinding

//DRY : DONT REPEAT YOURSELF
class LoginFragment : Fragment() {

    var _binding: FragmentLoginBinding? = null
    val binding get() = _binding!!
    private val args: LoginFragmentArgs by navArgs()
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localRepository = UserLocalRepository(requireContext())
        val authRepository = AuthRepository()
        val factory = LoginViewModelFactory(localRepository,authRepository)
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
        viewModel.checkTitleLogin(args.document)
        observedState()
        binding.cambiarUserBtn.setOnClickListener {
            viewModel.changeUser()
        }
        binding.loginBtn.setOnClickListener {
            viewModel.login(args.document,binding.claveEditText.text.toString() )
        }
    }



    private fun observedState() {
        viewModel.state.observe(viewLifecycleOwner) {state ->
            binding.loaderContainer.visibility = if(state == LoginState.Loading) View.VISIBLE else View.GONE
            when(state) {
                is LoginState.Idle -> {
                    binding.dniText.text = state.title
                }
                is LoginState.Error -> {
                    showToast(state.message)
                }
                is LoginState.Success -> {
                    goToHome()
                }
                is LoginState.BackNavigate -> {
                    findNavController().popBackStack()
                }
                else -> { Unit}
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