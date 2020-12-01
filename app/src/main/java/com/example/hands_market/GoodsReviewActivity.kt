package com.example.hands_market

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_goods_review.*
import org.w3c.dom.Text

// '리뷰 작성하기' 화면
class GoodsReviewActivity : AppCompatActivity() {

    val Gallery = 0

    val items = arrayOf("1", "2", "3", "4", "5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_review)

        // '첨부하기' 버튼 - 상품 이미지 업로드
        val goodsRevImgBtn : Button = findViewById(R.id.goodsRevImgBtn)
        goodsRevImgBtn.setOnClickListener{goodsRevAttachmentFileBtn()}

        // '등록하기' 버튼 - 상품 리뷰하기 등록
        val goodsRevCreateBtn = findViewById<Button>(R.id.goodsRevCreateBtn)
        goodsRevCreateBtn.setOnClickListener{
            val intent = Intent(this, GoodsDetailActivity::class.java)
            startActivity(intent)
        }

        goodsRevSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.goodsReview_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    // 리뷰하기 - 상품 이미지 첨부 경로 설정
    private fun goodsRevAttachmentFileBtn() {
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
                var bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
            }catch (e:Exception) {
                Toast.makeText(this,"$e", Toast.LENGTH_SHORT).show()
            }
        }
        else {}

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
