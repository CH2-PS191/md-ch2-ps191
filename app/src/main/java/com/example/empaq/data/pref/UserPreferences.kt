package com.example.empaq.data.pref

import android.content.Context

class UserPreferences(private val context: Context) {
    // Save gender and date of birth to local storage
    fun saveUserDetailsLocally(gender: String, dateOfBirth: String) {
        val sharedPreferences = context.getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("gender", gender)
        editor.putString("dateOfBirth", dateOfBirth)
        editor.apply()
    }

    // Retrieve gender and date of birth from local storage
    fun getUserDetailsLocally(): Pair<String, String> {
        val sharedPreferences = context.getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        return Pair(
            sharedPreferences.getString("gender", "") ?: "",
            sharedPreferences.getString("dateOfBirth", "") ?: ""
        )
    }
}