package com.example.empaq.ui.screen.authentication

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.CreateConversationResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel(private val repository: EmpaqRepository) : ViewModel() {
    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> get() = _isLoggedIn

    private val _conversationId = MutableStateFlow<String?>(null)
    val conversationId: StateFlow<String?> = _conversationId

    suspend fun createConversation() : CreateConversationResponse? {
        try {
            val response = repository.createConversation()
            if (response.success) {
                _conversationId.value = response.id
            } else {
                Log.d("AUTH", "${response.message}")
            }
            return response
        } catch (e: Exception) {
            return null
        }
    }
}