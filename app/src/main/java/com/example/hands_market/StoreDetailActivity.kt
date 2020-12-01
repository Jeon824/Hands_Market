package com.example.hands_market

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.Image
//import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.hands_market.MainActivity.Companion.DEFAULT_LAT
import com.example.hands_market.MainActivity.Companion.DEFAULT_LNG
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase
import java.net.URL

lateinit var storeImage : ImageView
lateinit var storeUpdateBtn : Button

lateinit var thisStore : Store
lateinit var storeName : TextView
lateinit var storeAddress : TextView
lateinit var buttonStoreDeleteBtn : Button
lateinit var addGoodsBtn : Button
lateinit var storeLayoutBtn : Button
lateinit var sId : String




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




        //선정된 정보 받기
        thisStore = Store(newIntent.getStringExtra("managerID"),
                newIntent.getStringExtra("storeName"),
                newIntent.getDoubleExtra("storeLat", MainActivity.DEFAULT_LAT),
                newIntent.getDoubleExtra("storeLng", MainActivity.DEFAULT_LNG),
                newIntent.getStringExtra("storeAddress"),
                storeImg, storeLayout,
                newIntent.getStringExtra("storeImgUrl"),
                newIntent.getStringExtra("storeKey"))

        storeName = findViewById(R.id.storeDetailNameText)
        storeName.text = thisStore.storeName
        storeAddress = findViewById(R.id.store_detail_store_address)
        storeAddress.text = thisStore.storeAddress

        // '배치도' 버튼 - 상점 배치도 조회
        storeLayoutBtn = findViewById(R.id.button_storeLayout)
        storeLayoutBtn.setOnClickListener(this)

        // '가게 삭제' 버튼 - 상점 삭제
        buttonStoreDeleteBtn = findViewById<Button>(R.id.button_storeState_delete)
        buttonStoreDeleteBtn.setOnClickListener(this)


        storeLayoutBtn = findViewById(R.id.button_storeLayout)
        storeLayoutBtn.setOnClickListener(this)

        var storeImageUrl = thisStore.storeImgurl
        var image_task: StoreListAdapter.URLtoBitmapTask = StoreListAdapter.URLtoBitmapTask()
        image_task = StoreListAdapter.URLtoBitmapTask().apply {
            url = URL("$storeImageUrl")
        }
        Log.d("StoreDetailActivity", "4s")
        var bitmap: Bitmap = image_task.execute().get()
        bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
        storeImage=findViewById<ImageView>(R.id.store_detail_store_image)
        storeImage.setImageBitmap(bitmap)

        sId= thisStore.SID.toString()
//sid 값 보내기기
        var fragment : Fragment=GoodsListFragment()
        var bundle : Bundle = Bundle(1)
        bundle.putString("sId", sId)
        //fragment로 번들 전달
        fragment.setArguments(bundle)
        val transaction: FragmentTransaction = getSupportFragmentManager().beginTransaction()
        transaction.replace(R.id.store_detail_goods_fragment, fragment).commit();


        var buttonGoodsAdd = findViewById<Button>(R.id.button_GoodsAdd)
        buttonGoodsAdd.setOnClickListener(this)

        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.storeDetail_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


//        var test = findViewById<Button>(R.id.test)
//        test.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.button_storeLayout -> {
                    val inflater: LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val pw: View = inflater.inflate(R.layout.pop_up_layout, null)
                    var width: Int = LinearLayout.LayoutParams.WRAP_CONTENT
                    var height: Int = LinearLayout.LayoutParams.WRAP_CONTENT
                    var focusable: Boolean = true

                    val puW: PopupWindow = PopupWindow(pw, 800, 600, focusable)
                    puW.contentView = pw

                    val layout: ImageView = pw.findViewById(R.id.pop_up_layout_img)
                    layout.setImageBitmap(thisStore.storeLayout)


                    puW.showAtLocation(v, Gravity.CENTER, 0, 0)

                    /*val intent = Intent(this, StoreLayoutCallActivity::class.java)
                    val stream : ByteArrayOutputStream = ByteArrayOutputStream()
                    thisStore.storeImg?.compress(Bitmap.CompressFormat.PNG,90,stream)
                    val storeLayout = stream.toByteArray()
                    intent.putExtra("storeLayout",storeLayout)
                    startActivity(intent)*/
                }

                R.id.button_storeState_delete -> {
                    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val myRef = database.getReference()
                    myRef.child("Stores").child("$sId").removeValue()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                }

                R.id.button_GoodsAdd -> {
//                    val a = sId.toString()
                    val intent = Intent(this, GoodsRegisterActivity::class.java)
                    intent.putExtra("storeKey", sId)
                    startActivity(intent)
                }
//                R.id.test->{
//                    val intent = Intent(this, GoodsRegisterActivity::class.java)
//                    intent.putExtra("storeKey", "111111")
//                    startActivity(intent)
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
