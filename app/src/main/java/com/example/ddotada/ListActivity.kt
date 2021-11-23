package com.example.ddotada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val btn_back = findViewById<ImageButton>(R.id.btn_back)
        btn_back.setOnClickListener {
            finish()
        }
    }
}