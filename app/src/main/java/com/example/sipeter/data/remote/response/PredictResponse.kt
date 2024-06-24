package com.example.sipeter.data.remote.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(
	@field:SerializedName("status_gizi")
	val statusGizi: String
)
