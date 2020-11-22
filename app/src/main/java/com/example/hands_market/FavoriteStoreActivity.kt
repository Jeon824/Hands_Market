package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

// 상점 즐겨찾기 화면
class FavoriteStoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_store)

        // 상품 즐겨찾기 목록 조회 화면으로 전환하는 버튼
        val favoriteGoodsBtn : Button = findViewById(R.id.favoriteGoodsBtn)
        favoriteGoodsBtn.setOnClickListener{
            val intent = Intent(this, FavoriteGoodsActivity::class.java)
            startActivity(intent)
        }

        // 상점 즐겨찾기 삭제 화면으로 넘어가는 버튼
        val deleteFavorite1Btn : Button = findViewById(R.id.deleteFavoriteBtn)
        deleteFavorite1Btn.setOnClickListener{
            val intent = Intent(this, DeleteFavoriteStoreActivity::class.java)
            startActivity(intent)
        }
    }
}
