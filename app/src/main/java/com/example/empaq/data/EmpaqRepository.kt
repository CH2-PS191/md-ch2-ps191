package com.example.empaq.data

import com.example.empaq.data.response.CreateConversationResponse
import com.example.empaq.data.response.ListConversationResponse
import com.example.empaq.data.response.ListPakarResponse
import com.example.empaq.data.response.ListSebayaResponse
import com.example.empaq.data.response.SendChatbotRequest
import com.example.empaq.data.response.SendChatbotResponse
import com.example.empaq.data.retrofit.ApiService

class EmpaqRepository(
    private val apiService: ApiService
){
    suspend fun getPakarList(): ListPakarResponse {
        return apiService.getPakar()
    }

    suspend fun getKonselorList(): ListSebayaResponse {
        return apiService.getKonselor()
    }

    suspend fun createConversation(): CreateConversationResponse {
        return apiService.createConversation()
    }

    suspend fun getConservationList(): ListConversationResponse {
        return apiService.getConversation()
    }

    suspend fun sendChatbotMessage(conversationId: String, message: SendChatbotRequest): SendChatbotResponse {
        return apiService.sendChatbotMessage(conversationId = conversationId, message = message)
    }

//    fun getSpecialist(): List<Specialist> {
//        return SpecialistDataResourceDummy.SpecialistList
//    }

//    suspend fun login(email: String, password: String, returnSecureToken: Boolean) : LoginResponse {
//        return apiService.getPakar
//    }

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