package com.example.financierajho.UI.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.financierajho.R
import com.example.financierajho.databinding.ActivityHomeBinding
import com.example.financierajho.databinding.FragmentDocumentLoginBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavView()
    }

    private fun setupBottomNavView(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        setSupportActionBar(binding.toolbar)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.productsFragment,
                R.id.benefitsFragment,
                R.id.myAccountFragment
            )
        )
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
        binding.bottonNavView.setupWithNavController(navController)
    }

}