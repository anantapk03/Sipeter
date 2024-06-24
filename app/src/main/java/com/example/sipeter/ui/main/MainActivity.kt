package com.example.sipeter.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.example.sipeter.R
import com.example.sipeter.databinding.ActivityMainBinding
import com.example.sipeter.ui.ResultState
import com.example.sipeter.ui.ViewModelFactory
import com.example.sipeter.ui.formpredict.PredictFormActivity
import com.example.sipeter.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        viewModel.getSession().observe(this){user ->
            if(!user.isLogin){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }

        binding.cardPredict.setOnClickListener {
            startActivity(Intent(this, PredictFormActivity::class.java))
        }

//        setTextApiResponse()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

//    private fun setTextApiResponse(){
//        viewModel.apiTest().observe(this){result ->
//            if(result != null){
//                when(result){
//                    is ResultState.Loading -> {
//                        showLoading(true)
//                    }
//                    is ResultState.Success-> {
//                        binding.tvResponseAPI.text = result.data.message
//                        showLoading(false)
//                    }
//                    is ResultState.Error -> {
//                        showLoading(false)
//                        showToast(result.error)
//                    }
//                }
//            }
//        }
//    }

    private fun logout(){

    }



}