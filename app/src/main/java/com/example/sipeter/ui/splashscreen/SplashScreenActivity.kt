package com.example.sipeter.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.sipeter.databinding.ActivitySplashScreenBinding
import com.example.sipeter.ui.ViewModelFactory
import com.example.sipeter.ui.login.LoginActivity
import com.example.sipeter.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private val viewModel by viewModels<SplashScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoAplikasi.alpha = 0f
        binding.logoAplikasi.animate().setDuration(4000).alpha(1f).withEndAction{
            viewModel.getSession().observe(this){
                if (!it.isLogin){
                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                } else{
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            }
        }
    }
}