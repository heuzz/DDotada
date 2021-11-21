package com.example.ddotada

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        startLoading()
    }

    private fun startLoading() {  // 딜레이주고 메인 엑티비티로 넘어가기 여기서 조건 주어서 처음 접속이면 스타팅 화면1 로
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(baseContext, precaution_before_starting1::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}