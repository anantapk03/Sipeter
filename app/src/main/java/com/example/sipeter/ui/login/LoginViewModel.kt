package com.example.sipeter.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sipeter.data.model.UserModel
import com.example.sipeter.repository.Repository
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: Repository): ViewModel()  {
    var userName : String = "Default_userName"
    var password : String = "Default_Password"
    fun loginUser() = repository.loginUser(userName, password)

    fun saveSession(user: UserModel){
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}