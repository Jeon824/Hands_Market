package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        val goods_request = findViewById<Button>(R.id.goods_request)

        val button_modify = findViewById<Button>(R.id.button_modify)

        val button_delete = findViewById<Button>(R.id.button_delete)

        val button_shopping_basket = findViewById<TextView>(R.id.button_shopping_basket)
        button_shopping_basket.setOnClickListener{
            val intent = Intent(this, ShoppingBasketActivity::class.java)
            startActivity(intent)
        }

        val button_purchase = findViewById<TextView>(R.id.button_purchase)

        val button_reserve = findViewById<TextView>(R.id.button_reserve)

        val button_review_write = findViewById<Button>(R.id.button_review_write)
        button_review_write.setOnClickListener{
            val intent = Intent(this, GoodsReviewActivity::class.java)
            startActivity(intent)
        }

        // bottom navigation 선언
        val goodsDetail_navigation = findViewById<BottomNavigationView>(R.id.goodsDetail_navigation)
        goodsDetail_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

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