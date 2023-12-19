package com.example.empaq.data.retrofit

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    val auth = FirebaseAuth.getInstance()
    fun getApiService(): ApiService {
        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val token = runBlocking {
                auth.currentUser?.getIdToken(false)?.await()?.token
            }
            val requestHeaders = req.newBuilder()
                .addHeader("Authorization", token.orEmpty())
                .build()
            chain.proceed(requestHeaders)
        }
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://capstone-ch2-ps191.et.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}