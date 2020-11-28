package com.example.hands_market

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

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

    }

    override fun onClick(v: View?) {

    }
}