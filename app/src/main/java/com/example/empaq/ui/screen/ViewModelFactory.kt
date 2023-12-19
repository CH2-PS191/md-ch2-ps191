package com.example.empaq.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.ui.screen.authentication.AuthViewModel
import com.example.empaq.ui.screen.chatbot.ChatbotViewModel
import com.example.empaq.ui.screen.pakar.SpecialistViewModel
import com.example.empaq.ui.screen.sebaya.KonselorViewModel

class ViewModelFactory(private val repository: EmpaqRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpecialistViewModel::class.java)) {
            return SpecialistViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(KonselorViewModel::class.java)) {
            return KonselorViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ChatbotViewModel::class.java)) {
            return ChatbotViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}