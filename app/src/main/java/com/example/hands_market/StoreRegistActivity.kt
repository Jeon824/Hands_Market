package com.example.hands_market

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase
const val DEFAULT_LAT_r :Double = 37.5740381
const val DEFAULT_LNG_r :Double = 126.97458
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase

//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase

class StoreRegistActivity : AppCompatActivity(), View.OnClickListener {

    val Gallery = 0

    private lateinit var marketNameInput :EditText
    private var sid="store"
    //private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_regist)

        val storeCreateBtn = findViewById<TextView>(R.id.storeCreateBtn)
        storeCreateBtn.setOnClickListener(this)

//        val database = Firebase.database
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")
        marketNameInput =findViewById(R.id.market_name)

        val storeRegSearchAddressBtn = findViewById<TextView>(R.id.storeRegSearchAddressBtn)

        val storeRegImgBtn : Button = findViewById(R.id.storeRegImgBtn)
        storeRegImgBtn.setOnClickListener{storeRegImg()}

        val storeRegStoreLayoutImgBtn : Button = findViewById(R.id.storeRegStoreLayoutImgBtn)
        storeRegStoreLayoutImgBtn.setOnClickListener{storeRegStoreLayoutImg()}

        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.storeRegist_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                //R.id.searchBtn
                R.id.storeCreateBtn -> {
                    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
                    val myRef = database.getReference()
                    val StoreOne = Store(
                        storeName = marketNameInput.text.toString(),
                        managerID = "1",
                        storeAddress = "2" ,
                        storeLat = DEFAULT_LAT_r,
                        storeLng =DEFAULT_LNG_r
                    )

                    println(StoreOne)
                   // myRef.child("Stores").push().setValue("hi")
                    myRef.child("Stores").push().setValue(StoreOne)
//                    Toast.makeText(StoreDetailActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun storeRegImg() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery)
    }

    private fun storeRegStoreLayoutImg() {
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
            }catch (e:Exception) {
                Toast.makeText(this,"$e", Toast.LENGTH_SHORT).show()
            }
        } else {}
        Toast.makeText(this, "업로드 되었습니다.", Toast.LENGTH_SHORT).show()
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
