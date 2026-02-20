package com.example.financierajho.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.financierajho.R
import com.example.financierajho.databinding.FragmentLoginBinding
import com.example.financierajho.databinding.FragmentLoginDocumentBinding


class LoginFragment : Fragment() {

    var _binding: FragmentLoginBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }




}