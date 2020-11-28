package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteGoodsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_goods)

        // '상점' 버튼 - 상점 즐겨찾기 화면으로 이동
        val favoriteStoreBtn2 : Button = findViewById(R.id.favoriteStoreBtn2)
        favoriteStoreBtn2.setOnClickListener{
            val intent = Intent(this, FavoriteStoreActivity::class.java)
            startActivity(intent)
        }

        // '삭제' 버튼 - 선택 항목 중 즐겨찾기 삭제
        val favoriteGoodsDelBtn : Button = findViewById(R.id.favoriteGoodsDelBtn)
        favoriteGoodsDelBtn.setOnClickListener{
            Toast.makeText(this@FavoriteGoodsActivity, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
        }

        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.favoriteGoods_navigation)
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
