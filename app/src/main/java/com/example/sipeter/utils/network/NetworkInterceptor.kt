package com.example.sipeter.utils.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class NetworkInterceptor (private val context: Context) : Interceptor{
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkConnection.isInternetAvailable(context)) {
            throw NoInternetException("Tidak ada koneksi internet")
        }
        return chain.proceed(chain.request())
    }
}

class NoInternetException(messege: String) : IOException(messege)