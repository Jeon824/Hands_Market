package com.example.hands_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

// 상점 즐겨찾기 삭제 화면
class DeleteFavoriteStoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_favorite_store)

        // 상점 즐겨찾기 목록을 전체 선택 하기 위한 버튼
        val favoriteStoreAllCheckBtn : Button = findViewById(R.id.favoriteStoreAllCheckBtn)

        // 상점 즐겨찾기 목록 최종 삭제
        val deleteFavoriteStoreBtn : Button = findViewById(R.id.deleteFavoriteStoreBtn)
        deleteFavoriteStoreBtn.setOnClickListener{
            Toast.makeText(this@DeleteFavoriteStoreActivity, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
        }

    }
}