package com.example.sipeter.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.sipeter.data.model.UserModel
import com.example.sipeter.repository.Repository

class SplashScreenViewModel (private val repository: Repository): ViewModel(){
    fun getSession(): LiveData<UserModel>{
        return repository.getSession().asLiveData()
    }
}