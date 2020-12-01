package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class MypageActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val button_change_memberdata = findViewById<Button>(R.id.button_change_memberdata)
        button_change_memberdata.setOnClickListener{
            val intent = Intent(this, Mypage_change::class.java)
            startActivity(intent)
        }

        val button_purchaselist = findViewById<Button>(R.id.button_purchaselist)
        button_purchaselist.setOnClickListener{
            val intent = Intent(this, PurchasedListActivity::class.java)
            startActivity(intent)
        }
        var button_manager = findViewById<Button>(R.id.button_manager)
        button_manager.setOnClickListener(this)


        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.mypage_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.button_manager->{
                    val intent = Intent(this, ManagerActivity::class.java)
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
