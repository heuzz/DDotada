package com.example.ddotada

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import java.security.MessageDigest

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        startLoading()

        try {
            val info =
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            val signatures = info.signingInfo.apkContentsSigners
            val md = MessageDigest.getInstance("SHA")
            for (signature in signatures) {
                val md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val key = String(Base64.encode(md.digest(), 0))
                Log.d("Hash key:", "!!!!!!!$key!!!!!!")
            }
        } catch (e: Exception) {
            Log.e("name not found", e.toString())
        }
    }

    private fun startLoading() {  // 딜레이주고 메인 엑티비티로 넘어가기 여기서 조건 주어서 처음 접속이면 스타팅 화면1 로
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(baseContext, precaution_before_starting1::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}