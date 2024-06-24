package com.example.sipeter.data.remote.response

import com.google.gson.annotations.SerializedName

data class ApiTestResponse(

	@field:SerializedName("message")
	val message: String
)
