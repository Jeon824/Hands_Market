package com.example.hands_market

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class GoodsDetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var thisGoods : Goods

    var reserveBtn : Button? = null;
    var requestGoods : Button? = null;
    var editBtn : Button? = null;
    var deleteBtn : Button? = null;
    var deleteReviewBtn : Button? = null;
    var addToBasketBtn : Button? = null;
    var writeReviewBtn : Button? = null;
    var goodsImage : ImageView? = null;
    var favoriteStatus : ImageView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        val button_favorite : ImageView = findViewById(R.id.button_favorite)
        button_favorite.setOnClickListener{
            button_favorite.isSelected = true
        }

        var imgArray : ByteArray? = intent.getByteArrayExtra("storeImg")
        goodsImage = findViewById<ImageView>(R.id.goods_detail_image)

        // '입고하기' 버튼
        val goods_request : Button = findViewById(R.id.goods_request)

        // 수정
        val button_modify : Button = findViewById(R.id.button_modify)

        // 삭제
        val button_delete : Button = findViewById(R.id.button_delete)

        // '장바구니' 버튼
        val button_shopping_basket : TextView = findViewById(R.id.button_shopping_basket)

        // '결제 서비스' 버튼
        val button_purchase : TextView = findViewById(R.id.button_purchase)

        // '예약하기' 버튼
        val button_reserve :TextView = findViewById(R.id.button_reserve)

        // '리뷰 쓰기' 버튼 - 리뷰 쓰기 화면으로 이동
        val button_review_write : Button = findViewById(R.id.button_review_write)
        button_review_write.setOnClickListener{
            val intent = Intent(this, GoodsReviewActivity::class.java)
            startActivity(intent)
        }

        // bottom navigation 선언
        val goodsDetail_navigation = findViewById<BottomNavigationView>(R.id.goodsDetail_navigation)
        goodsDetail_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onClick(v: View?) {
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
