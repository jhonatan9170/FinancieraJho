package com.example.financierajho

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.financierajho.databinding.FragmentLoginDocumentBinding

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


}