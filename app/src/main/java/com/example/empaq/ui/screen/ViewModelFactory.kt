package com.example.empaq.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.ui.screen.specialist.SpecialistViewModel

class ViewModelFactory(private val repository: EmpaqRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpecialistViewModel::class.java)) {
            return SpecialistViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}