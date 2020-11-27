package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteStoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_store)

        // '상품' 버튼 - 상품 즐겨찾기 조회 버튼
        val favoriteGoodsBtn1 : Button = findViewById(R.id.favoriteGoodsBtn1)
        favoriteGoodsBtn1.setOnClickListener{
            val intent = Intent(this, FavoriteGoodsActivity::class.java)
            startActivity(intent)
        }

        // '삭제' 버튼 - 즐겨찾기 삭제 버튼
        val favoriteStoreDelBtn : Button = findViewById(R.id.favoriteStoreDelBtn)
        favoriteStoreDelBtn.setOnClickListener{
            Toast.makeText(this@FavoriteStoreActivity, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
        }

        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.favoriteStore_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    // bottom navigation 버튼 출력 함수
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_favorite -> {
                val intent = Intent(this,FavoriteActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_mypage -> {
                val intent = Intent(this,MypageActivity::class.java)
                startActivity(intent)
            }
        }
        false
    }
}
