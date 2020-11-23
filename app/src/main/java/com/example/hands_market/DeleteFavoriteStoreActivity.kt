package com.example.hands_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class DeleteFavoriteStoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_favorite_store)

        val favoriteStoreAllCheckBtn : Button = findViewById(R.id.favoriteStoreAllCheckBtn)

        val deleteFavoriteStoreBtn : Button = findViewById(R.id.deleteFavoriteStoreBtn) // 장바구니 상품 최종 삭제
        deleteFavoriteStoreBtn.setOnClickListener{
            Toast.makeText(this@DeleteFavoriteStoreActivity, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
        }

    }
}