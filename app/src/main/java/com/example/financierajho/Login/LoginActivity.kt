package com.example.financierajho.Login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.financierajho.Home.HomeActivity
import com.example.financierajho.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val prefs = this.getSharedPreferences("personal_data", Context.MODE_PRIVATE)
        val document = prefs.getString("DOCUMENT", "").toString()
        if (document.count()==8) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(binding.root)


    }
}