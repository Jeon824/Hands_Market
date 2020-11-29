package com.example.hands_market

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase


//lateinit var sIdOfGoods : String

class GoodsRegisterActivity : AppCompatActivity(), View.OnClickListener {
    val Gallery = 0
    private lateinit var goodsNameInput :EditText
    private lateinit var goodsPriceInput :EditText
    private lateinit var goodsSizeInput :EditText
    private lateinit var goodsColorInput :EditText
    private lateinit var goodsCountInput :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_register)

        Log.d("GOODSRegister", "11111111")

        val goodsCreateBtn = findViewById<TextView>(R.id.goodsCreateBtn)
        goodsCreateBtn.setOnClickListener(this)

        val goodsRegStoreImgBtn : Button = findViewById(R.id.goodsRegStoreImgBtn)
        goodsRegStoreImgBtn.setOnClickListener{goodsRegStoreImg()}

        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.goodsRegister_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        goodsNameInput=findViewById<EditText>(R.id.goodsName)
        goodsPriceInput=findViewById<EditText>(R.id.goodsPrice)
        goodsSizeInput=findViewById<EditText>(R.id.goodsSize)
        goodsColorInput=findViewById<EditText>(R.id.goodsColor)
        goodsCountInput=findViewById<EditText>(R.id.goodsCount)
        val intent = intent
        val myData = intent.getStringExtra("storeKey")
        Log.d("storedetail", "$myData")
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                //R.id.searchBtn
                R.id.goodsCreateBtn -> {
                    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val myRef = database.getReference()
                    val GoodsOne = Goods(
                            storeName = "1",
                            managerID = "1",
                            name = "2",
                            imageUrl = "1",
                            price = 1,
                            location = "1",
                            size = 1,
                            count = 1
                    )
                    myRef.child("Stores").child("Goods").push().setValue(GoodsOne)

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun goodsRegStoreImg() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Gallery) {
            var dataUri = data?.data
            try {
                var bitmap: Bitmap =
                    MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
            }catch (e: Exception) {
                Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
            }
        } else {}
        Toast.makeText(this, "성공적으로 업로드", Toast.LENGTH_SHORT).show()
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
