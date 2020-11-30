package com.example.hands_market

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*


//lateinit var sIdOfGoods : String

class GoodsRegisterActivity : AppCompatActivity(), View.OnClickListener {
    val Gallery = 0
    private lateinit var goodsNameInput :EditText
    private lateinit var goodsPriceInput :EditText
    private lateinit var goodsSizeInput :EditText
    private lateinit var goodsColorInput :EditText
    private lateinit var goodsCountInput :EditText
    private lateinit var dataUri : Uri
    lateinit var GoodsUrl :String
    private lateinit var showGoodsImg :ImageView
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

        showGoodsImg = findViewById<ImageView>(R.id.showGoodsImg)


//        val intent = intent
//        val myData = intent.getStringExtra("storeKey")
//        Log.d("storedetail", "$myData")
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
            dataUri = data?.data!!
            try {
                var bitmap: Bitmap =
                    MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
                    showGoodsImg.setImageBitmap(bitmap)
                if (dataUri != null) {
                    //storage
                    var Storage = FirebaseStorage.getInstance()
//                    var storeRef = Storage.reference

                    //Unique한 파일명을 만들자.
                    val formatter = SimpleDateFormat("yyyyMMHH_mmss")
                    val now = Date()
                    val filename: String = formatter.format(now).toString() + ".png"

                    //storage 주소와 폴더 파일명을 지정해 준다.
                    val GoodsRef: StorageReference = Storage.getReference().child("GoodsImages").child(filename)

                    //로컬 파일에서 업로드
                    GoodsRef!!.putFile(dataUri!!)
                            .addOnSuccessListener {
                                GoodsRef!!.downloadUrl.addOnSuccessListener{
                                    Log.d("StoreRegistActivity","$it")
                                    GoodsUrl=it.toString()
                                }.addOnFailureListener {
//
                                }
                            }
                            .addOnFailureListener {
                                // Handle unsuccessful uploads
                                // ...
                                Toast.makeText(this, "저장 실패.", Toast.LENGTH_SHORT).show()
                            }
                }
                else{
                    Toast.makeText(this, "저장 실패22", Toast.LENGTH_SHORT).show()
                }

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
