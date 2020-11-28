package com.example.hands_market

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
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
        favoriteStatus = findViewById(R.id.button_favorite)
        var imgArray : ByteArray? = intent.getByteArrayExtra("storeImg")
        goodsImage = findViewById<ImageView>(R.id.goods_detail_image)

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
