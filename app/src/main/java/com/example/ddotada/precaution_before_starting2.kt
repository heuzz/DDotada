package com.example.ddotada

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class precaution_before_starting2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_precaution_before_starting2)

        val btn_next = findViewById<ImageButton>(R.id.btn_precaution_before_starting2_next)
        btn_next.setOnClickListener {
            val intent = Intent(this, precaution_before_starting3::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            overridePendingTransition(R.anim.anim_slide_out_right,R.anim.anim_slide_in_left)
        }
    }
}