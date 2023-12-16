package com.example.empaq.data.retrofit

import com.example.empaq.data.response.LoginRequest
import com.example.empaq.data.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("accounts:signInWithPassword?key=AIzaSyB3kIRhAQM9JWeDNwu05fi0nzcRKTWC63M")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("returnSecureToken") returnSecureToken: Boolean,
    ): LoginResponse
}