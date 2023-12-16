package com.example.empaq.di

import android.content.Context
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.retrofit.ApiConfig
import com.example.empaq.data.retrofit.ApiService

object Injection {
    fun provideRepository(context: Context, apiService: ApiService) : EmpaqRepository {
        val apiService = ApiConfig().getApiService()
        return EmpaqRepository.getInstance(apiService)
    }
}