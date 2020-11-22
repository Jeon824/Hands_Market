package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

// 상품 등록 화면
class GoodsRegisterActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_register)

        // 상점 내 상품 위치 배치도 첨부파일 버튼
        val accretionClickBtn = findViewById<Button>(R.id.accretionGoodsBtn)
        accretionClickBtn.setOnClickListener(this)

        // 상품에 관한 정보를 등록하기 위한 버튼
        val goodsCreateBtn = findViewById<TextView>(R.id.goodsCreateBtn)
        goodsCreateBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                //R.id.searchBtn
                R.id.goodsCreateBtn -> {
                    val intent = Intent(this, GoodsDetailActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}