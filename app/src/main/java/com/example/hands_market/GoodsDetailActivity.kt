package com.example.hands_market

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase
import java.net.URL

lateinit var goodsName : TextView
lateinit var gId : String
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



    var DEFAULT_Price:Int =0
    var DEFAULT_Count:Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        val button_favorite : ImageView = findViewById(R.id.button_favorite)
        button_favorite.setOnClickListener{
            button_favorite.isSelected = true
        }


        // '입고하기' 버튼
        val goods_request : Button = findViewById(R.id.goods_request)

        // 수정
//        val button_modify : Button = findViewById(R.id.button_modify)

        // 삭제
        val button_delete : Button = findViewById(R.id.button_delete)
        button_delete.setOnClickListener(this)

        // '장바구니' 버튼
        val button_shopping_basket : TextView = findViewById(R.id.button_shopping_basket)

        // '결제 서비스' 버튼
        val button_purchase : TextView = findViewById(R.id.button_purchase)

        // '예약하기' 버튼
        val button_reserve :TextView = findViewById(R.id.button_reserve)

        // '리뷰 쓰기' 버튼 - 리뷰 쓰기 화면으로 이동
        val button_review_write : Button = findViewById(R.id.button_review_write)
        button_review_write.setOnClickListener(this)

        // bottom navigation 선언
        val goodsDetail_navigation = findViewById<BottomNavigationView>(R.id.goodsDetail_navigation)
        goodsDetail_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val newIntent : Intent = intent
        var imgArrayG : ByteArray? = intent.getByteArrayExtra("Gimage")
        var goodsImg: Bitmap? = null

        if(imgArrayG!=null) {
            goodsImg = BitmapFactory.decodeByteArray(imgArrayG, 0, imgArrayG.size)
        }
        thisGoods= Goods(newIntent.getStringExtra("managerID"),
                newIntent.getStringExtra("storeId"),
                newIntent.getStringExtra("Gname"),
                goodsImg,
                newIntent.getIntExtra("Gprice", DEFAULT_Price),
                newIntent.getStringExtra("Glocation"),
                newIntent.getStringExtra("Gsize"),
                newIntent.getStringExtra("Gcolor"),
                newIntent.getIntExtra("Gcount", DEFAULT_Count),
                newIntent.getStringExtra("GimageUrl"),
                newIntent.getStringExtra("GoodsKey"))

        Log.d("StoreDetailActivity", "name--------------------------------------------------------")
        goodsName = findViewById(R.id.goods_detail_name)
        goodsName.text = thisGoods.name

//sId
        Log.d("goodsDetailActivity", "4")
        sId=thisGoods.storeId
        Log.d("goodsDetailActivity", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        Log.d("goodsDetailActivity", "$sId")
//        var reviewFragment_SID : Fragment = ReivewFragment()
//        var bundle_SID : Bundle = Bundle(1)
//        bundle_SID.putString("sId", sId)
//        Log.d("StoreDetailActivity", "3")
//        //fragment로 번들 전달
//        reviewFragment_SID.setArguments(bundle_SID)
//        val transaction_SID: FragmentTransaction = getSupportFragmentManager().beginTransaction()
//        transaction_SID.replace(R.id.goods_review_fragment, reviewFragment_SID).commit();

//이미지보여주기
        Log.d("goodsDetailActivity", "image--------------------")
        var GoodsImageUrl = thisGoods.imageUrl
        if(GoodsImageUrl.toString()=="0"){

        }
        else{
            var image_task: StoreListAdapter.URLtoBitmapTask = StoreListAdapter.URLtoBitmapTask()
            image_task = StoreListAdapter.URLtoBitmapTask().apply {
                url = URL("$GoodsImageUrl")
            }
            Log.d("goodsDetailActivity", "4s")
            var bitmap: Bitmap = image_task.execute().get()
            bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            goodsImage = findViewById<ImageView>(R.id.goods_detail_image)
            goodsImage?.setImageBitmap(bitmap)
            Log.d("goodsetailActivity", "1")
        }


        //gid 값 보내기기
        gId= thisGoods.gId.toString()
        Log.d("goodsetailActivity", "$gId")
        var reviewFragment : Fragment = ReivewFragment()
        var bundle : Bundle = Bundle(2)
        bundle.putString("gId", gId)
        bundle.putString("sId", sId)
        Log.d("goodsDetailActivity", "3")
        //fragment로 번들 전달
        reviewFragment.setArguments(bundle)
        val transaction: FragmentTransaction = getSupportFragmentManager().beginTransaction()
        transaction.replace(R.id.goods_review_fragment, reviewFragment).commit();


    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.button_review_write -> {
                    val intent = Intent(this, GoodsReviewActivity::class.java)
                    intent.putExtra("storeKey", sId)
                    Log.d("goodsdetail~~","111111111111111111111111")
                    Log.d("goodsdetail~~","$sId")
                    intent.putExtra("GoodsKey", gId)
                    Log.d("goodsdetail~~","122222222222222")
                    Log.d("goodsdetail~~","$gId")
                    startActivity(intent)
                }
                R.id.button_delete ->{
                    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val myRef = database.getReference()
                    var sid=thisGoods.storeId.toString()
                    var gid=thisGoods.gId.toString()
                    myRef.child("Stores").child("$sid").child("Goods").child("$gId").removeValue()
                    finish()
                }
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
