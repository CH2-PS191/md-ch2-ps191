package com.example.empaq.di

import com.example.empaq.data.EmpaqRepository

object Injection {
    fun provideRepository() : EmpaqRepository {
        return EmpaqRepository.getInstance()
    }
}