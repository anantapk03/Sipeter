package com.example.sipeter.repository

import androidx.lifecycle.liveData
import com.example.sipeter.data.SettingPreferences
import com.example.sipeter.data.model.UserModel
import com.example.sipeter.data.remote.response.ApiTestResponse
import com.example.sipeter.data.remote.response.LoginResponse
import com.example.sipeter.data.remote.response.PredictFormResponse
import com.example.sipeter.data.remote.response.PredictResponse
import com.example.sipeter.data.remote.retrofit.ApiService
import com.example.sipeter.ui.ResultState
import com.example.sipeter.utils.network.NoInternetException
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.Query
import java.io.IOException

class Repository private constructor(
    private val apiService: ApiService,
    private val settingPreferences: SettingPreferences
){
    fun apiTest() = liveData<ResultState<ApiTestResponse>> {
        emit(ResultState.Loading)
        try {
            val successApiTest = apiService.apiTest()
            emit(ResultState.Success(successApiTest))
        } catch (e: HttpException){
            emit(ResultState.Error(e.message.toString()))
        } catch (e:NoInternetException){
            emit(ResultState.Error(e.message.toString()))
        } catch (e: IOException){
            emit(ResultState.Error(e.message.toString()))
        }
    }

    fun loginUser(userName: String, password:String) = liveData<ResultState<LoginResponse>> {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.login(userName, password)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException){
            emit(ResultState.Error(e.message.toString()))
        } catch (e:NoInternetException){
            emit(ResultState.Error(e.message.toString()))
        } catch (e: IOException){
            emit(ResultState.Error(e.message.toString()))
        }
    }

    suspend fun saveSession(user: UserModel){
        settingPreferences.saveSession(user)
    }

    fun getSession(): Flow<UserModel>{
        return settingPreferences.getSession()
    }
    suspend fun logout(){
        settingPreferences.logout()
    }

    fun getPredict(
        namaBalita: String,
        umur : Int,
        jenisKelamin : Int,
        tinggiBadan : Float
    ) = liveData<ResultState<PredictFormResponse>> {
        emit(ResultState.Loading)
        try {
            val successGetResponse = apiService.getPredict(
                namaBalita,umur,jenisKelamin,tinggiBadan
            )
            emit(ResultState.Success(successGetResponse))
        } catch (e: HttpException){
            emit(ResultState.Error(e.message.toString()))
        } catch (e:NoInternetException){
            emit(ResultState.Error(e.message.toString()))
        } catch (e: IOException){
            emit(ResultState.Error(e.message.toString()))
        }
    }

    companion object{
        @Volatile
        private var instance : Repository? = null

        fun getInstance(
            apiService: ApiService,
            settingPreferences: SettingPreferences
        ) : Repository =
            instance ?: synchronized(this){
                instance ?: Repository(apiService, settingPreferences)
            }.also { instance = it }
    }




}