package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ShoppingBasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_basket)

        val basketAllCheckBtn : Button = findViewById(R.id.basketAllCheckBtn) // 상품을 전체 선택하는 버튼

        val basketPayBtn : Button = findViewById(R.id.basketPayBtn) // 결제 창으로 넘어가는 버튼

        val basketDeleteBtn : Button = findViewById(R.id.basketDeleteBtn) // 장바구니 상품 삭제 화면으로 넘어가는 버튼
        basketDeleteBtn.setOnClickListener{
            val intent = Intent(this, DeleteBasketActivity::class.java)
            startActivity(intent)
        }
    }
}