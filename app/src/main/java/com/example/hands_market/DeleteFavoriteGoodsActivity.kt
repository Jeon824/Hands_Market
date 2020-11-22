package com.example.hands_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

// 상품 즐겨찾기 삭제 화면
class DeleteFavoriteGoodsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_favorite_goods)

        // 상품 즐겨찾기 목록을 전체 선택 하기 위한 버튼
        val favoriteGoodsAllCheckBtn : Button = findViewById(R.id.favoriteGoodsAllCheckBtn)

        // 상품 즐겨찾기 목록 최종 삭제
        val deleteFavoriteGoodsBtn = findViewById<Button>(R.id.deleteFavoriteGoodsBtn)
        deleteFavoriteGoodsBtn.setOnClickListener{
            Toast.makeText(this@DeleteFavoriteGoodsActivity, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}