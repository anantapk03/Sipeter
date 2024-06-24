package com.example.sipeter.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sipeter.di.Injection
import com.example.sipeter.repository.Repository
import com.example.sipeter.ui.formpredict.PredictFormViewModel
import com.example.sipeter.ui.login.LoginViewModel
import com.example.sipeter.ui.main.MainViewModel
import com.example.sipeter.ui.splashscreen.SplashScreenViewModel

class ViewModelFactory (private val repository: Repository): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java)->{
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java)->{
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SplashScreenViewModel::class.java)->{
                SplashScreenViewModel(repository) as T
            }
            modelClass.isAssignableFrom(PredictFormViewModel::class.java)->{
                PredictFormViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class: "+ modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context):ViewModelFactory{
            if(INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(Injection.proviedRepository(context))
                }
            }
            return  INSTANCE as ViewModelFactory
        }
    }
}