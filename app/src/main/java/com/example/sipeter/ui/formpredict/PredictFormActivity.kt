package com.example.sipeter.ui.formpredict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.sipeter.R
import com.example.sipeter.databinding.ActivityPredictFormBinding
import com.example.sipeter.ui.ResultState
import com.example.sipeter.ui.ViewModelFactory

class PredictFormActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPredictFormBinding
    private val viewModel by viewModels<PredictFormViewModel>{
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPredict.setOnClickListener {
            val namaBalita = binding.namaBalitaEd.text.toString()
            val umur= binding.umurBalitaEd.text.toString().toInt()
            val jenisKelamin = binding.jenisKelaminBalitaEd.text.toString().toInt()
            val tinggiBadan = binding.tinggiBalitaEd.text.toString().toFloat()
            getPredict(
                namaBalita, umur, jenisKelamin, tinggiBadan
            )
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading (isLoading : Boolean){
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setTextResult(text: String){
        binding.resultPredict.text = text
    }

    private fun getPredict(
        namaBalita: String,
        umur : Int,
        jenisKelamin : Int,
        tinggiBadan : Float
    ){
        viewModel.getPredict(
            namaBalita, umur, jenisKelamin, tinggiBadan
        ).observe(this){ result->
            if(result!=null){
                when(result){
                    is ResultState.Success -> {
                        showLoading(false)
//                        setTextResult(result.data.hasilPrediksi)
                        if (result.data.hasilPrediksi == "0"){
                            setTextResult("normal")
                        }
                        if (result.data.hasilPrediksi == "1"){
                            setTextResult("severely stunted")
                        }
                        if (result.data.hasilPrediksi == "2"){
                            setTextResult("stunted")
                        }
                        if (result.data.hasilPrediksi == "3"){
                            setTextResult("Tinggi Normal")
                        }
                    }
                    is ResultState.Loading -> {
                        showLoading(true)
                    }
                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error.toString())
                    }
                }
            }

        }
    }

}