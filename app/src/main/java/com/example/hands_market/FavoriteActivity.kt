package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

// 메인 즐겨찾기 화면
class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        // 상점 즐겨찾기 목록 조회 화면으로 넘어가는 버튼
        val favoriteStoreBtn = findViewById<Button>(R.id.favoriteStoreBtn)
        favoriteStoreBtn.setOnClickListener{
            val intent = Intent(this, FavoriteStoreActivity::class.java)
            startActivity(intent)
        }

        // 상품 즐겨찾기 목록 조회 화면으로 넘어가는 버튼
        val favoriteGoodsBtn = findViewById<Button>(R.id.favoriteGoodsBtn)
        favoriteGoodsBtn.setOnClickListener{
            val intent = Intent(this, FavoriteGoodsActivity::class.java)
            startActivity(intent)
        }
    }
}
