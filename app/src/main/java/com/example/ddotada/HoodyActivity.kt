package com.example.ddotada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class HoodyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoody)

        val btn_back = findViewById<ImageButton>(R.id.btn_back2)
        btn_back.setOnClickListener {
            finish()
        }
    }
}