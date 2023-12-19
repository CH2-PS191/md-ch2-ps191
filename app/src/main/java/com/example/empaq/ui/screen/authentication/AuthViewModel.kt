package com.example.empaq.ui.screen.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.CreateConversationResponse
import com.example.empaq.data.response.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: EmpaqRepository) : ViewModel() {
    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> get() = _isLoggedIn

//    fun login(email: String, password: String) {
//        // Logika otentikasi
//        // Set _isLoggedIn menjadi true setelah berhasil login
//        _isLoggedIn.value = true
//    }

//    suspend fun login(email: String, password: String, returnSecureToken: Boolean) : LoginResponse {
//        return repository.login(email, password, returnSecureToken)
//    }

    // State for conversationId
    private val _conversationId = MutableStateFlow<String?>(null)
    val conversationId: StateFlow<String?> = _conversationId

    // Function to create a conversation
    suspend fun createConversation() : CreateConversationResponse? {
        try {
            val response = repository.createConversation()
            if (response.success) {
                _conversationId.value = response.id
            } else {
                // Handle the error case if needed
            }
            return response
        } catch (e: Exception) {
            // Handle exceptions here
            return null
        }
    }

    fun logout() {
        // Logika logout
        // Set _isLoggedIn menjadi false setelah logout
        _isLoggedIn.value = false
    }
}