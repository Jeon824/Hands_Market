package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val favoriteStoreBtn = findViewById<Button>(R.id.favoriteStoreBtn) // 즐겨찾기 조회 (상점) 버튼
        favoriteStoreBtn.setOnClickListener{
            val intent = Intent(this, FavoriteStoreActivity::class.java)
            startActivity(intent)
        }

        val favoriteGoodsBtn = findViewById<Button>(R.id.favoriteGoodsBtn) // 즐겨찾기 조회 (상품) 버튼
        favoriteGoodsBtn.setOnClickListener{
            val intent = Intent(this, FavoriteGoodsActivity::class.java)
            startActivity(intent)
        }
    }
}
