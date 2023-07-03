package com.example.statussaver

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import com.example.statussaver.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    lateinit var splashScreenBinding : ActivitySplashScreenBinding
    lateinit var prefs:SharedPreferences;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)

        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if(prefs.getBoolean(("isdark"),false).equals(true)){

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        Handler().postDelayed(Runnable {

            var i = Intent(this,MainActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK and  Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
            finish()

        },1500)



    }
}