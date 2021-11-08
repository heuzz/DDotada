package com.example.ddotada

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class precaution_before_starting1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_precaution_before_starting1)

        val btn_next = findViewById<ImageButton>(R.id.precaution_before_starting1_btn_next)
        btn_next.setOnClickListener{
            val intent = Intent(this,precaution_before_starting2::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
    }
}