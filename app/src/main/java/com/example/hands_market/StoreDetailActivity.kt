package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class StoreDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)

//        val addGoodsBtn = findViewById<TextView>(R.id.addGoodsBtn)
//        addGoodsBtn.setOnClickListener(this)
    }

//    override fun onClick(v: View?) {
//        if (v != null) {
//            when (v.id) {
//                //R.id.searchBtn
//                R.id.addGoodsBtn -> {
//                    val intent = Intent(this, GoodsRegisterActivity::class.java)
//                    startActivity(intent)
//                }
//            }
//        }
//    }
}