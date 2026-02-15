package com.example.financierajho.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.financierajho.databinding.ActivityHomeBinding
import com.example.financierajho.databinding.FragmentDocumentLoginBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}