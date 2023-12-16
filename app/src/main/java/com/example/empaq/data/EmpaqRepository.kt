package com.example.empaq.data

import com.example.empaq.data.response.LoginRequest
import com.example.empaq.data.response.LoginResponse
import com.example.empaq.data.retrofit.ApiService
import com.example.empaq.model.Specialist
import com.example.empaq.model.SpecialistDataResourceDummy

class EmpaqRepository private constructor(
    private val apiService: ApiService
){

    fun getSpecialist(): List<Specialist> {
        return SpecialistDataResourceDummy.SpecialistList
    }

    suspend fun login(email: String, password: String, returnSecureToken: Boolean) : LoginResponse {
        return apiService.login(email, password, returnSecureToken)
    }

    companion object {
        @Volatile
        private var instance: EmpaqRepository? = null

        fun getInstance(
            apiService: ApiService
        ): EmpaqRepository =
            instance ?: synchronized(this) {
                EmpaqRepository(apiService).apply {
                    instance = this
                }
            }
    }
}