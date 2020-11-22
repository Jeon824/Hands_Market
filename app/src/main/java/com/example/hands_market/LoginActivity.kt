package com.example.hands_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //내아버로 로그인(loginAutoImgBtn)을 누르면 네이버 아아디로 로그인
        val loginAutoImgBtn = findViewById<View>(R.id.loginAutoImgBtn)
    }
}