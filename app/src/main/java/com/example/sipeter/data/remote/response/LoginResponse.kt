package com.example.sipeter.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("nip")
	val nip: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("level")
	val level: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("username")
	val username: String
)
