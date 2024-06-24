package com.example.sipeter.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.sipeter.data.model.UserModel
import com.example.sipeter.ui.main.MainActivity
import com.example.sipeter.databinding.ActivityLoginBinding
import com.example.sipeter.ui.ResultState
import com.example.sipeter.ui.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            viewModel.userName = binding.userNameEditText.text.toString()
            viewModel.password = binding.passwordEditText.text.toString()
            postLogin()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading (isLoading : Boolean){
        binding.ProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun postLogin(){
        viewModel.loginUser().observe(this){result ->
            if(result!=null){
                when(result){
                    is ResultState.Loading -> {
                        showLoading(true)
                    }
                    is ResultState.Success -> {
                        viewModel.saveSession(
                            UserModel(
                                userName = binding.userNameEditText.text.toString(),
                                token = "token ini token",
                                name = result.data.nama,
                                role = result.data.level,
                                isLogin = true
                            )
                        )
                        showLoading(false)
                        showToast("Selamat datang, ${result.data.nama}")
                        val intent= Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
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