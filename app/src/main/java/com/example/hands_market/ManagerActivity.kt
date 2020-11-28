package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ManagerActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)

        val reserveListBtn = findViewById<TextView>(R.id.reserveListBtn) // 예약 화면으로 들어가는 버튼
        reserveListBtn.setOnClickListener{
            val intent = Intent(this, ReserveListActivity::class.java)
            startActivity(intent)
        }

        val requestGoodsListBtn = findViewById<TextView>(R.id.requestGoodsListBtn) // 입고 화면으로 들어가는 버튼
        requestGoodsListBtn.setOnClickListener{
            val intent = Intent(this, RequestGoodsListActivity::class.java)
            startActivity(intent)
        }

        val addStoreBtn = findViewById<TextView>(R.id.addStoreBtn)
        addStoreBtn.setOnClickListener(this)

        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.manager_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                //R.id.searchBtn
                R.id.addStoreBtn -> {
                    val intent = Intent(this, StoreRegistActivity::class.java)
                    startActivity(intent)
                }
            }
        }
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
