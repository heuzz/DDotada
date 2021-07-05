package com.example.ddotada

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //해쉬키 얻기
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

        val btn_signup = findViewById<ImageButton>(R.id.btn_signup)

        btn_signup.setOnClickListener {
            val intent = Intent(this, SingupActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
    }
}