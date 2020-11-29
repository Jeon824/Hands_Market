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


const val DEFAULT_LAT_r :Double = 37.5740381
const val DEFAULT_LNG_r :Double = 126.97458
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase

//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase

class StoreRegistActivity : AppCompatActivity(), View.OnClickListener {

    val Gallery = 0

    private lateinit var marketNameInput :EditText
    private lateinit var showImgInput :ImageView
    private lateinit var showLayoutImg :ImageView
    private lateinit var showInput :ImageView
    private lateinit var dataUri : Uri
    private lateinit var dataImgUri : Uri
    private lateinit var dataLayoutUri : Uri
    lateinit var url :String

    //private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_regist)

        val storeCreateBtn = findViewById<TextView>(R.id.storeCreateBtn)
        storeCreateBtn.setOnClickListener(this)

        marketNameInput = findViewById(R.id.market_name)
        showImgInput = findViewById<ImageView>(R.id.showImg)
        showLayoutImg = findViewById<ImageView>(R.id.showLayoutImg)

        val storeRegSearchAddressBtn = findViewById<TextView>(R.id.storeRegSearchAddressBtn)

        val storeRegImgBtn : Button = findViewById(R.id.storeRegImgBtn)
        storeRegImgBtn.setOnClickListener{storeRegImg(showImgInput)}

        val storeRegStoreLayoutImgBtn : Button = findViewById(R.id.storeRegStoreLayoutImgBtn)
        storeRegStoreLayoutImgBtn.setOnClickListener{storeRegStoreLayoutImg(showLayoutImg)}

        // bottom navigation 선언
        val storeRegist_navigation = findViewById<BottomNavigationView>(R.id.storeRegist_navigation)
        storeRegist_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                //R.id.searchBtn
                R.id.storeCreateBtn -> {
                    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val myRef = database.getReference()
                    val StoreOne = Store(
                            storeName = marketNameInput.text.toString(),
                            managerID = "1",
                            storeAddress = "2",
                            storeLat = DEFAULT_LAT_r,
                            storeLng = DEFAULT_LNG_r,
                            storeImgurl=url,
                            SID = "0"
                    )
                    myRef.child("Stores").push().setValue(StoreOne)

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
//                R.id.showImg->{
//                    showInput=showImgInput
//                    storeRegImg()
//                }
            }
        }
    }

    private fun storeRegImg(showImgInput: ImageView) {
        showInput=showImgInput
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery)
    }

    private fun storeRegStoreLayoutImg(showLayoutImg: ImageView) {
        showInput=showLayoutImg
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        var showImg = findViewById<ImageView>(R.id.showImg)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Gallery) {
            dataUri = data?.data!!
            try {
                var bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
                showInput.setImageBitmap(bitmap) //Img눌렀을 경우

                if (dataUri != null) {
                    //storage
                    var Storage = FirebaseStorage.getInstance()
//                    var storeRef = Storage.reference

                //Unique한 파일명을 만들자.
                val formatter = SimpleDateFormat("yyyyMMHH_mmss")
                val now = Date()
                val filename: String = formatter.format(now).toString() + ".png"

                //storage 주소와 폴더 파일명을 지정해 준다.
                val storeRef: StorageReference = Storage.getReference().child("storeImages").child(filename)

//                    //메모리에서 업로드
//                showImgInput.isDrawingCacheEnabled = true
//                showImgInput.buildDrawingCache()
//                val bitmap = (showImgInput.drawable as BitmapDrawable).bitmap
//                val baos = ByteArrayOutputStream()
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                val data = baos.toByteArray()
//                storeRef!!.putBytes(data).addOnSuccessListener {
//                            url=storeRef.downloadUrl.toString()
//                            Toast.makeText(this, "업로드 되었습니다.", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener {
//                            // Handle unsuccessful uploads
//                            // ...
//                            Toast.makeText(this, "저장 실패.", Toast.LENGTH_SHORT).show()
//                        }
                //로컬 파일에서 업로드
                    storeRef!!.putFile(dataUri!!)
                        .addOnSuccessListener {
                            storeRef!!.downloadUrl.addOnSuccessListener{
                                Log.d("StoreRegistActivity","$it")
                                url=it.toString()
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
//        Toast.makeText(this, "업로드 되었습니다.", Toast.LENGTH_SHORT).show()
    }

//    private fun saveToFirebase(storeImgurl:String){
//
//    }

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
