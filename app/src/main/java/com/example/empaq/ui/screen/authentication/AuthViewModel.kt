package com.example.empaq.ui.screen.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.LoginResponse

class AuthViewModel(private val repository: EmpaqRepository) : ViewModel() {
    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> get() = _isLoggedIn

//    fun login(email: String, password: String) {
//        // Logika otentikasi
//        // Set _isLoggedIn menjadi true setelah berhasil login
//        _isLoggedIn.value = true
//    }

    suspend fun login(email: String, password: String, returnSecureToken: Boolean) : LoginResponse {
        return repository.login(email, password, returnSecureToken)
    }

    fun logout() {
        // Logika logout
        // Set _isLoggedIn menjadi false setelah logout
        _isLoggedIn.value = false
    }
}