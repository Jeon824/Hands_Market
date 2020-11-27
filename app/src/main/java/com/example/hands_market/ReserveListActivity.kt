package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ReserveListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_list)

        val requestGoodsListBtn2 = findViewById<TextView>(R.id.requestGoodsListBtn2) //입고 화면으로 바뀌는 버튼
        requestGoodsListBtn2.setOnClickListener{
            val intent = Intent(this, RequestGoodsListActivity::class.java)
            startActivity(intent)
        }
    }
}
