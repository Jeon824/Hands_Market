package com.example.hands_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Mypage_change : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_change)

        // 회원정보를 수정하기 버튼
        val memberModifyBtn : Button = findViewById(R.id.memberModifyBtn)
    }
}