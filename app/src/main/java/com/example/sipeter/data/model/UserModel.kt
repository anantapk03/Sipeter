package com.example.sipeter.data.model

data class UserModel(
    val userName: String,
    val token: String,
    val name: String,
    val role: String,
    val isLogin: Boolean = false
)
