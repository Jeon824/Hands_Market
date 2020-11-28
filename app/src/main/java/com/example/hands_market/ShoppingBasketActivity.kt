package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShoppingBasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_basket)

        // '전체 선택' 버튼 - 장바구니 내 상품을 전체 선택
        val basketAllCheckBtn: Button = findViewById(R.id.basketAllCheckBtn)

        // '결제' 버튼 - 결제 화면으로 이동
        val basketPayBtn: Button = findViewById(R.id.basketPayBtn)

        // '삭제' 버튼 - 선택된 만큼 장바구니 내 상품 삭제
        val basketDeleteBtn: Button = findViewById(R.id.basketDeleteBtn)
        basketDeleteBtn.setOnClickListener {
            Toast.makeText(this@ShoppingBasketActivity, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
        }

        // bottom navigation 선언
        val basket_navigation = findViewById<BottomNavigationView>(R.id.basket_navigation)
        basket_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    // bottom navigation 버튼 출력 함수
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
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
