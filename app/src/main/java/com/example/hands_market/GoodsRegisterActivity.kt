package com.example.hands_market

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase

class GoodsRegisterActivity : AppCompatActivity(), View.OnClickListener {

    val Gallery = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_register)

        val goodsCreateBtn = findViewById<TextView>(R.id.goodsCreateBtn)
        goodsCreateBtn.setOnClickListener(this)

        val goodsRegStoreImgBtn : Button = findViewById(R.id.goodsRegStoreImgBtn)
        goodsRegStoreImgBtn.setOnClickListener{goodsRegStoreImg()}

        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.favoriteGoods_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                //R.id.searchBtn
                R.id.goodsCreateBtn -> {
                    val intent = Intent(this, GoodsDetailActivity::class.java)
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
            }catch (e:Exception) {
                Toast.makeText(this,"$e", Toast.LENGTH_SHORT).show()
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
