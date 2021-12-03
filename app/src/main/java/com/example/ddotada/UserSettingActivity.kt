package com.example.ddotada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.database.*

class UserSettingActivity : AppCompatActivity() {

    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = firebaseDatabase.getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_setting)

        val text_nickname = findViewById<EditText>(R.id.text_nickname)
        val text_name = findViewById<EditText>(R.id.text_name)
        val text_phone = findViewById<EditText>(R.id.text_phone)
        val text_id = findViewById<EditText>(R.id.text_id)
        val text_pw = findViewById<EditText>(R.id.text_pw)
        Log.d("suuuuuuuuu",call("name"))
        text_nickname.setText("N.Hoody")
        text_name.setText("admin")
        text_phone.setText("010-1234-1234")
        text_id.setText("admin")
        text_pw.setText("admin")

        val btn_back = findViewById<ImageButton>(R.id.btn_back4)
        btn_back.setOnClickListener {
            finish()
        }
    }
    private fun call(name : String): String {
        var data : String = ""
        Log.d("start","데이터가져오기 시작")
        try {
            databaseReference.child("User").child("admin").child("${name}").get().addOnSuccessListener{
                data = it.value.toString()
                Log.d("suuuuu","가져오기 성공" + it.value.toString())

            }.addOnFailureListener {
                Log.d("fail","못가져옴")
            }
            return data
        }catch (e:Exception){
            Log.d("ffail","가져오기 실패")
        }
        return data
    }


}