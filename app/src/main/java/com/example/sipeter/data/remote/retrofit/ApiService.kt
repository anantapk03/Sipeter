package com.example.sipeter.data.remote.retrofit

import com.example.sipeter.data.remote.response.ApiTestResponse
import com.example.sipeter.data.remote.response.LoginResponse
import com.example.sipeter.data.remote.response.PredictFormResponse
import com.example.sipeter.data.remote.response.PredictResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/predict")
    suspend fun getPredict(
        @Query("nama_balita") namaBalita: String,
        @Query("umur") umur : Int,
        @Query("jenis_kelamin") jenisKelamin : Int,
        @Query("tinggi_badan") tinggiBadan : Float
    ): PredictFormResponse

    @GET("/")
    suspend fun apiTest() : ApiTestResponse
    @FormUrlEncoded
    @POST("/login")
    suspend fun login(
        @Field ("username") username: String,
        @Field("password") password : String
    ) : LoginResponse
}