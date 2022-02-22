package com.example.lamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper


//Responsible of showing splash screen activity in the beginning of application and navigate to next screen
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed( {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}