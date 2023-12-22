package com.example.empaq.data.pref

import android.content.Context

class UserPreferences(private val context: Context) {
    fun saveUserDetailsLocally(gender: String, dateOfBirth: String) {
        val sharedPreferences = context.getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("gender", gender)
        editor.putString("dateOfBirth", dateOfBirth)
        editor.apply()
    }

    fun getUserDetailsLocally(): Pair<String, String> {
        val sharedPreferences = context.getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        return Pair(
            sharedPreferences.getString("gender", "") ?: "",
            sharedPreferences.getString("dateOfBirth", "") ?: ""
        )
    }
}