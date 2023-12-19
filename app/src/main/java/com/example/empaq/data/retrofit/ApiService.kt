package com.example.empaq.data.retrofit

import com.example.empaq.data.response.CreateConversationResponse
import com.example.empaq.data.response.ListConversationResponse
import com.example.empaq.data.response.ListPakarResponse
import com.example.empaq.data.response.ListSebayaResponse
import com.example.empaq.data.response.SendChatbotRequest
import com.example.empaq.data.response.SendChatbotResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("user/pakar")
    suspend fun getPakar() : ListPakarResponse

    @GET("user/sebaya")
    suspend fun getKonselor() : ListSebayaResponse

    @GET("conversation")
    suspend fun getConversation() : ListConversationResponse

    @POST("conversation/create")
    suspend fun createConversation() : CreateConversationResponse

    @POST("conversation/{conversationid}/create")
    suspend fun sendChatbotMessage(
        @Path("conversationid") conversationId : String,
        @Body message : SendChatbotRequest
    ) : SendChatbotResponse
}