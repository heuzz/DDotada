package com.example.ddotada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn_signup = findViewById<ImageButton>(R.id.btn_signup)

        btn_signup.setOnClickListener {

        }
    }
}