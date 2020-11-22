package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ManagerActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)

        // 가게+ 버튼 누릴 시
        val addStoreBtn = findViewById<TextView>(R.id.addStoreBtn)
        addStoreBtn.setOnClickListener(this)

        // 콜 알린 내역 버튼 누를 시
        val callListBtn = findViewById<TextView>(R.id.callListBtn)
        callListBtn.setOnClickListener(this)

        // 예약 내역 버튼 누를 시
        val reserveListBtn = findViewById<TextView>(R.id.reserveListBtn)
        reserveListBtn.setOnClickListener {
            val intent = Intent(this, ReserveListActivity::class.java)
            startActivity(intent)
        }

        // 입고 버튼 누를 시
        val requestGoodsListBtn = findViewById<TextView>(R.id.requestGoodsListBtn)
        requestGoodsListBtn.setOnClickListener{
            val intent = Intent(this, RequestGoodsListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                //R.id.searchBtn
                R.id.addStoreBtn -> {
                    val intent = Intent(this, StoreRegistActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}