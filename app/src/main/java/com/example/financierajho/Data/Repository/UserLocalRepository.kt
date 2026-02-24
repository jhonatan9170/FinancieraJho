package com.example.financierajho.Data.Repository

import android.content.Context

class UserLocalRepository(val context: Context) {

    private var prefs = context.getSharedPreferences("personal_data", Context.MODE_PRIVATE)

    var name: String
        get() = prefs.getString("NAME","").toString()
        set(name) = prefs.edit().putString("NAME",name).apply()

    var document: String
        get() = prefs.getString("DOCUMENT","").toString()
        set(document) = prefs.edit().putString("DOCUMENT",document).apply()

}