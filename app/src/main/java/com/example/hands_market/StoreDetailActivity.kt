package com.example.hands_market

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

lateinit var storeImage : ImageView
lateinit var storeUpdateBtn : Button

lateinit var thisStore : Store
lateinit var storeName : TextView
lateinit var storeAddress : TextView

const val DEFAULT_LAT :Double = 37.5740381
const val DEFAULT_LNG :Double = 126.97458

class StoreDetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)
        val newIntent : Intent = intent
        var imgArray : ByteArray? = intent.getByteArrayExtra("storeImg")
        var storeImg: Bitmap? = null
        var storeLayout: Bitmap? = null

        if(imgArray!=null) {
            storeImg = BitmapFactory.decodeByteArray(imgArray, 0, imgArray.size)
        }

        imgArray = intent.getByteArrayExtra("storeLayout")

        if(imgArray!=null) {
            storeLayout = BitmapFactory.decodeByteArray(imgArray, 0, imgArray.size)
        }



        var thisStore = Store(newIntent.getStringExtra("managerID"),
                newIntent.getStringExtra("storeName"),
                newIntent.getDoubleExtra("storeLat",DEFAULT_LAT),
                newIntent.getDoubleExtra("storeLng",DEFAULT_LNG),
                newIntent.getStringExtra("storeAddress"),
                storeImg,storeLayout)


        storeName = findViewById(R.id.store_detail_store_name)
        storeName.text = thisStore.storeName
        storeAddress.text = thisStore.storeAddress






    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                //R.id.searchBtn
               /*R.id.addGoodsBtn -> {
                    val intent = Intent(this, GoodsRegisterActivity::class.java)
                    startActivity(intent)
                }*/
            }
        }
    }

}