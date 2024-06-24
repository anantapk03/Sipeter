package com.example.sipeter.data.remote.retrofit

import android.content.Context
import com.example.sipeter.utils.network.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    fun getApiService(context:Context) : ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val networkInterceptor = NetworkInterceptor(context)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(networkInterceptor)
            .build()

        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://dmri127-apisipeter.hf.space")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}