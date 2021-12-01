package com.example.ddotada

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SingupActivity : AppCompatActivity() {

    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = firebaseDatabase.getReference()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        //변수 초기화

        var nickname:String? = null
        var name:String? = null
        var phone:String? = null
        var password:String? = null
        var passwordcheck:String? = null
        var id:String? = null
        var error : Int = 0
        val OPEN_GALLERY = 1
        val btn_back = findViewById<ImageButton>(R.id.btn_back5)

        btn_back.setOnClickListener {
            finish()
        }
    }

}