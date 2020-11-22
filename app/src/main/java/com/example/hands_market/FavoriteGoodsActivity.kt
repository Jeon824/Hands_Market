package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FavoriteGoodsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_goods)

        val favoriteStoreBtn : Button = findViewById(R.id.favoriteStoreBtn) // 즐겨찾기 조회 (조회) 버튼
        favoriteStoreBtn.setOnClickListener{
            val intent = Intent(this, FavoriteStoreActivity::class.java)
            startActivity(intent)
        }

        val deleteFavorite2Btn : Button = findViewById(R.id.deleteFavorite2Btn) // 즐겨찾기 삭제 버튼
        deleteFavorite2Btn.setOnClickListener{
            val intent = Intent(this, DeleteFavoriteGoodsActivity::class.java)
            startActivity(intent)
        }
    }
}