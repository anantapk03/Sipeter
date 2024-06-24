package com.example.sipeter.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sipeter.data.model.UserModel
import com.example.sipeter.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel (private val repository: Repository):ViewModel() {

    fun apiTest() = repository.apiTest()

    fun getSession() : LiveData<UserModel>{
        return repository.getSession().asLiveData()
    }

    fun logout(){
        viewModelScope.launch {
            repository.logout()
        }
    }

}