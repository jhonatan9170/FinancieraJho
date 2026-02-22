package com.example.financierajho.UI.Login

import android.app.Activity
import android.content.Intent
import androidx.navigation.findNavController
import com.example.financierajho.UI.Home.HomeActivity

class LoginRouter(private val activity: Activity) {
    fun goToHome() {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }


}