package com.example.lamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
    }
    fun signUp(view:View){
        var intent: Intent = Intent(this,SignUpActivity::class.java)
        startActivity(intent)
    }
}