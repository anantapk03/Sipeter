package com.example.sipeter.ui.formpredict

import androidx.lifecycle.ViewModel
import com.example.sipeter.repository.Repository

class PredictFormViewModel(private val repository: Repository):ViewModel()  {

    fun getPredict(
        namaBalita: String,
        umur : Int,
        jenisKelamin : Int,
        tinggiBadan : Float
    ) = repository.getPredict(namaBalita, umur, jenisKelamin, tinggiBadan)
}