package com.example.sipeter.data.remote.response

import com.google.gson.annotations.SerializedName

data class PredictFormResponse(

	@field:SerializedName("nama_balita")
	val namaBalita: String,

	@field:SerializedName("umur")
	val umur: Int,

	@field:SerializedName("tinggi_badan")
	val tinggiBadan: Any,

	@field:SerializedName("id_histori")
	val idHistori: Int,

	@field:SerializedName("hasil_prediksi")
	val hasilPrediksi: String,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: Int
)
