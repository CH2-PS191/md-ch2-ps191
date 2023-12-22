package com.example.empaq.data.retrofit

import com.example.empaq.data.response.AddSpecialistRequest
import com.example.empaq.data.response.AddSpecialistResponse
import com.example.empaq.data.response.CreateConversationResponse
import com.example.empaq.data.response.ListConversationResponse
import com.example.empaq.data.response.ListKonselorConversationResponse
import com.example.empaq.data.response.ListPakarConversationResponse
import com.example.empaq.data.response.ListPakarResponse
import com.example.empaq.data.response.ListSebayaResponse
import com.example.empaq.data.response.SendChatbotRequest
import com.example.empaq.data.response.SendChatbotResponse
import com.example.empaq.data.response.SendUserRequest
import com.example.empaq.data.response.SendUserResponse
import com.example.empaq.data.response.UpdateProfileResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("user/pakar")
    suspend fun getPakar() : ListPakarResponse

    @GET("user/sebaya")
    suspend fun getKonselor() : ListSebayaResponse

    @GET("conversation")
    suspend fun getConversation() : ListConversationResponse

    @GET("conversation/sebaya")
    suspend fun getConversationSebaya() : ListKonselorConversationResponse

    @GET("conversation/pakar")
    suspend fun getConversationPakar() : ListPakarConversationResponse

    @POST("conversation/create")
    suspend fun createConversation() : CreateConversationResponse

    @POST("conversation/{conversationid}/create")
    suspend fun sendChatbotMessage(
        @Path("conversationid") conversationId : String,
        @Body message : SendChatbotRequest
    ) : SendChatbotResponse

    @POST("conversation/{conversationid}/selfcreate")
    suspend fun sendUserMessage(
        @Path("conversationid") conversationId : String,
        @Body message : SendUserRequest
    ) : SendUserResponse

    @PUT("conversation/{conversationid}/update")
    suspend fun addSpecialist(
        @Path("conversationid") conversationId : String,
        @Body pakarUid : AddSpecialistRequest
    ) : AddSpecialistResponse

    @Multipart
    @PUT("user/update")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ) : UpdateProfileResponse
}