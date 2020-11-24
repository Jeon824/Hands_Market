package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RequestGoodsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_goods_list)

        val reserveListBtn3 = findViewById<TextView>(R.id.reserveListBtn3) // 예약 화면으로 바뀌는 버튼
        reserveListBtn3.setOnClickListener{
            val intent = Intent(this, ReserveListActivity::class.java)
            startActivity(intent)
        }
    }
}