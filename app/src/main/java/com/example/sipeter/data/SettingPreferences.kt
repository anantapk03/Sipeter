package com.example.sipeter.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.sipeter.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "session")

class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>){

    suspend fun saveSession(user: UserModel){
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = user.userName
            preferences[ROLE_KEY] = user.role
            preferences[IS_LOGIN_KEY] = true
            preferences[NAME_KEY] = user.name
            preferences[TOKEN_KEY] = user.token
        }
    }

     fun getSession(): Flow<UserModel>{
        return dataStore.data.map { preferences ->
          UserModel(
              preferences[USERNAME_KEY] ?: "",
              preferences[TOKEN_KEY] ?: "",
              preferences[NAME_KEY] ?: "",
              preferences[ROLE_KEY] ?: "",
              preferences[IS_LOGIN_KEY] ?: false,
          )
        }
    }

    suspend fun logout(){
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


    companion object{
        @Volatile
        private var INSTANCE: SettingPreferences? = null

        private val USERNAME_KEY = stringPreferencesKey("username")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val NAME_KEY = stringPreferencesKey("name")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")
        private val ROLE_KEY = stringPreferencesKey("role")

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences{
            return INSTANCE ?: synchronized(this){
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}