package com.example.hands_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class DeleteBasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_basket)

        val deleteAllCheckBtn : Button = findViewById(R.id.deleteAllCheckBtn) // 장바구니 상품 삭제 전체선택 버튼

        val deleteFinalBtn : Button = findViewById(R.id.deleteFinalBtn) // 장바구니 상품 최종 삭제
        deleteFinalBtn.setOnClickListener{
            Toast.makeText(this@DeleteBasketActivity, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}