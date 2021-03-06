package com.example.ddotada

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.security.MessageDigest

class LoginActivity : AppCompatActivity() {
    // 파이어베이스 사용
    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = firebaseDatabase.getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //해시키 얻기(카카오맵을 사용하기 위해서 해시키가 필요함 Logcat에서 Hash Key 입력하면 해당 해시키가 나옴)
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


        // 초기화(사용할것들은 항상 필수로 매칭을 해줘야함)
        val btn_login = findViewById<ImageButton>(R.id.login_btn_login)
        val btn_signup = findViewById<ImageButton>(R.id.login_btn_signup)
        val input_id = findViewById<EditText>(R.id.login_intput_id)
        val input_pw = findViewById<EditText>(R.id.login_input_pw)
        var id:String = ""
        var pw:String = ""
        var id_check : String = ""
        var pw_check : String = ""

        //회원가입 버튼 동작
        btn_signup.setOnClickListener {
            val intent = Intent(this, SingupActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
        //로그인 버튼 동작
        btn_login.setOnClickListener {
            id = input_id.text.toString()
            pw = input_pw.text.toString()
            var pw_check = ""
            try {
                id_check = databaseReference.child("User").child("${id}").key.toString()
                databaseReference.child("User").child("${id}").child("pw").get().addOnSuccessListener{
                    pw_check = it.value.toString()
                    if(id == id_check && pw == pw_check){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    }else {
                        Log.d("logintest","로그인실패")
                        Log.d("id",id)
                        Log.d("idcheck",id_check)
                        Log.d("pw",pw)
                        Log.d("id",pw_check)
                    }

                }.addOnFailureListener {
                    Log.d("fail","비번 못가져옴")
                }

            } catch (e:Exception){

            }

        }



    }
}