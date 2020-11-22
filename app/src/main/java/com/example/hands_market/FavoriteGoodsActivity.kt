package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

// 상품 즐겨찾기 화면
class FavoriteGoodsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_goods)

        // 상점 즐겨찾기 목록 조회 화면으로 전환하는 버튼
        val favoriteStoreBtn : Button = findViewById(R.id.favoriteStoreBtn)
        favoriteStoreBtn.setOnClickListener{
            val intent = Intent(this, FavoriteStoreActivity::class.java)
            startActivity(intent)
        }

        // 상품 즐겨찾기 삭제 화면으로 넘어가는 버튼
        val deleteFavorite2Btn : Button = findViewById(R.id.deleteFavoriteBtn)
        deleteFavorite2Btn.setOnClickListener{
            val intent = Intent(this, DeleteFavoriteGoodsActivity::class.java)
            startActivity(intent)
        }
    }
}