package com.example.hands_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class DeleteFavoriteGoodsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_favorite_goods)

        val favoriteGoodsAllCheckBtn : Button = findViewById(R.id.favoriteGoodsAllCheckBtn)

        val deleteFavoriteGoodsBtn = findViewById<Button>(R.id.deleteFavoriteGoodsBtn)
        deleteFavoriteGoodsBtn.setOnClickListener{
            Toast.makeText(this@DeleteFavoriteGoodsActivity, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}