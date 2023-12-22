package com.example.empaq.data

import android.net.Uri
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
import com.example.empaq.data.retrofit.ApiService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

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
    suspend fun getConservationSebayaList(): ListKonselorConversationResponse {
        return apiService.getConversationSebaya()
    }
    suspend fun getConservationPakarList(): ListPakarConversationResponse {
        return apiService.getConversationPakar()
    }

    suspend fun updateSpecialist(conversationId: String, pakarUid: AddSpecialistRequest): AddSpecialistResponse {
        return apiService.addSpecialist(conversationId, pakarUid)
    }

    suspend fun sendChatbotMessage(conversationId: String, message: SendChatbotRequest): SendChatbotResponse {
        return apiService.sendChatbotMessage(conversationId = conversationId, message = message)
    }

    suspend fun sendUserMessage(conversationId: String, message: SendUserRequest): SendUserResponse {
        return apiService.sendUserMessage(conversationId, message)
    }

    suspend fun updateProfilePicture(file: File) : UpdateProfileResponse {
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        return try {
            val response = apiService.uploadImage(body)
            updateFirebaseUserProfile(response.publicUrl)
            response
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun updateFirebaseUserProfile(photoUrl: String) {
        try {
            val user = FirebaseAuth.getInstance().currentUser
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(photoUrl))
                .build()

            user?.updateProfile(profileUpdates)?.await()
        } catch (e: Exception) {
            // Handle error jika update ke Firebase gagal
            throw e
        }
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