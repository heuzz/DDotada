package com.example.ddotada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.isVisible

class HoodyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoody)

        val btn_back = findViewById<ImageButton>(R.id.btn_back2)
        val info =findViewById<ImageView>(R.id.info)
        btn_back.setOnClickListener {
            finish()
        }
        val btn_info = findViewById<ImageButton>(R.id.btn_info)
        btn_info.setOnClickListener {
            if(info.isVisible == false){
                info.visibility = View.VISIBLE
            }else{
                info.visibility = View.INVISIBLE
            }
        }
    }
}