package com.example.sipeter.di

import android.content.Context
import com.example.sipeter.data.SettingPreferences
import com.example.sipeter.data.dataStore
import com.example.sipeter.data.remote.retrofit.ApiConfig
import com.example.sipeter.repository.Repository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun proviedRepository(context: Context):Repository{
        val settingPreferences = SettingPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService(context)
        return Repository.getInstance(apiService, settingPreferences)
    }
}