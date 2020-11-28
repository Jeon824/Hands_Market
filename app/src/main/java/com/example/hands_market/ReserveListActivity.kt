package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ReserveListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_list)

        val requestGoodsListBtn2 = findViewById<TextView>(R.id.requestGoodsListBtn2) //입고 화면으로 바뀌는 버튼
        requestGoodsListBtn2.setOnClickListener{
            val intent = Intent(this, RequestGoodsListActivity::class.java)
            startActivity(intent)
        }

        // bottom navigation 선언
        val reserveList_navigation = findViewById<BottomNavigationView>(R.id.reserveList_navigation)
        reserveList_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    // bottom navigation 버튼 출력 함수
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_mypage -> {
                val intent = Intent(this, MypageActivity::class.java)
                startActivity(intent)
            }
        }
        false
    }
}
