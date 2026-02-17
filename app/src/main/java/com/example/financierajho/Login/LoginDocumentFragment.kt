package com.example.financierajho.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.financierajho.Home.HomeActivity
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextBtn.setOnClickListener {
            val document = binding.dniEditTxt.text.toString()
            val prefs = requireContext().getSharedPreferences("personal_data", Context.MODE_PRIVATE)
            prefs.edit().putString("DOCUMENT",document).apply()
            val intent = Intent(requireActivity(),HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }


    }

}