package com.example.koinmvvmretrofit.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferenceManager(private val context: Context) {
    var sharedPreference: SharedPreferences =
        context.getSharedPreferences("USER_PREF", MODE_PRIVATE)
    var editor: SharedPreferences.Editor

    init {
        editor = sharedPreference.edit()
    }

    fun putString(name: String) {
        editor.putString("NAME", name)
        editor.commit()
    }

    fun getString(): String? {
        return sharedPreference.getString("NAME", "")
    }


}