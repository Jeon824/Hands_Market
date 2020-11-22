package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ReserveListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_list) // 예약 화면

        //입고 요청 내역 클릭 시, 입고 내역 화면(RequestGoodsListActivity)으로 바뀌는 버튼
        val requestGoodsListBtn2 = findViewById<TextView>(R.id.requestGoodsListBtn2)
        requestGoodsListBtn2.setOnClickListener{
            val intent = Intent(this, RequestGoodsListActivity::class.java)
            startActivity(intent)
        }
    }
}