package com.example.hands_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

// 상점 내 삼품 화면
class GoodsDetailActivity : AppCompatActivity() {

    val reserveBtn : Button? = null;
    val requestGoods : Button? = null;
    val editBtn : Button? = null;
    val deleteBtn : Button? = null;
    val deleteReviewBtn : Button? = null;
    val addToBasketBtn : Button? = null;
    val writeReviewBtn : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
    }
}