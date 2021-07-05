package com.example.ddotada

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SingupActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        //변수 초기화
        val edit_nickname = findViewById<EditText>(R.id.edit_nickname)
        val edit_name = findViewById<EditText>(R.id.edit_id)
        val edit_phone = findViewById<EditText>(R.id.edit_phone)
        val edit_password = findViewById<EditText>(R.id.edit_password)
        val edit_passwordcheck = findViewById<EditText>(R.id.edit_passwordcheck)
        val btn_change_profile = findViewById<ImageButton>(R.id.btn_change_profile_image)
        val btn_signup2 = findViewById<ImageButton>(R.id.btn_signup2)

        var nickname:String? = null
        var name:String? = null
        var phone:String? = null
        var password:String? = null
        var passwordcheck:String? = null
        var error : Int = 0

        btn_change_profile.setOnClickListener {

        }

        btn_signup2.setOnClickListener {
            nickname = edit_nickname.text.toString()
            name = edit_name.text.toString()
            phone = edit_phone.text.toString()
            password = edit_password.text.toString()
            passwordcheck = edit_passwordcheck.text.toString()
            if(nickname == "" || name == "" || phone == "" || password == "" || password != passwordcheck){
                error = 1
            }
            else{
                error = 0
            }
            if(error == 1) {
                when {
                    nickname == "" -> {
                        Toast.makeText(this, "닉네임을 작성하지 않았습니다.",Toast.LENGTH_SHORT).show()
                    }
                    name == "" -> {
                        Toast.makeText(this,"이름을 작성하지 않았습니다.",Toast.LENGTH_SHORT).show()
                    }
                    phone == "" -> {
                        Toast.makeText(this,"전화번호를 작성하지 않았습니다.",Toast.LENGTH_SHORT).show()
                    }
                    password == "" -> {
                        Toast.makeText(this,"비밀번호를 작성하지 않았습니다.",Toast.LENGTH_SHORT).show()
                    }
                    password != passwordcheck -> {
                        Toast.makeText(this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                Toast.makeText(this,"회원가입이 되었습니다.",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            }

        }

    }

}