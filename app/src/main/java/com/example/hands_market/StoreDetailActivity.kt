package com.example.hands_market

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var storeImage : ImageView
lateinit var storeUpdateBtn : Button

lateinit var thisStore : Store
lateinit var storeName : TextView
lateinit var storeAddress : TextView
lateinit var buttonStoreDeleteBtn : Button
lateinit var addGoodsBtn : Button
lateinit var storeLayoutBtn : Button

const val DEFAULT_LAT :Double = 37.5740381
const val DEFAULT_LNG :Double = 126.97458

class StoreDetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var thisStore : Store

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



        thisStore = Store(newIntent.getStringExtra("managerID"),
            newIntent.getStringExtra("storeName"),
            newIntent.getDoubleExtra("storeLat",DEFAULT_LAT),
            newIntent.getDoubleExtra("storeLng",DEFAULT_LNG),
            newIntent.getStringExtra("storeAddress"),
            storeImg, storeLayout)

        storeName = findViewById(R.id.storeDetailNameText)
        storeName.text = thisStore.storeName
        storeAddress = findViewById(R.id.store_detail_store_address)
        storeAddress.text = thisStore.storeAddress
        buttonStoreDeleteBtn = findViewById<Button>(R.id.button_storeState_delete)
        addGoodsBtn  = findViewById<Button>(R.id.button_goodsAdd)
        storeLayoutBtn = findViewById(R.id.button_storeLayout)
        storeLayoutBtn.setOnClickListener(this)


        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.storeDetail_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                //R.id.searchBtn
                /*R.id.addGoodsBtn -> {
                     val intent = Intent(this, GoodsRegisterActivity::class.java)
                     startActivity(intent)
                 }*/
                R.id.button_storeLayout -> {
                    val inflater : LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val pw : View = inflater.inflate(R.layout.pop_up_layout,null)
                    var width : Int = LinearLayout.LayoutParams.WRAP_CONTENT
                    var height : Int = LinearLayout.LayoutParams.WRAP_CONTENT
                    var focusable : Boolean = true

                    val puW : PopupWindow = PopupWindow(pw,800,600,focusable)
                    puW.contentView = pw

                    val layout :ImageView = pw.findViewById(R.id.pop_up_layout_img)
                    layout.setImageBitmap(thisStore.storeLayout)


                    puW.showAtLocation(v, Gravity.CENTER, 0 , 0)

                    /*val intent = Intent(this, StoreLayoutCallActivity::class.java)
                    val stream : ByteArrayOutputStream = ByteArrayOutputStream()
                    thisStore.storeImg?.compress(Bitmap.CompressFormat.PNG,90,stream)
                    val storeLayout = stream.toByteArray()
                    intent.putExtra("storeLayout",storeLayout)
                    startActivity(intent)*/
                }

//                R.id.buttonStoreDeleteBtn ->{
//
//                }
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
