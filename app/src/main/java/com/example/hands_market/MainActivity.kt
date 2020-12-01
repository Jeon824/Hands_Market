package com.example.hands_market

//import com.google.firebase.ktx.Firebase
//import com.google.firebase.database.ktx.database
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var setAddress: TextView
    private lateinit var searchBtn: ImageButton
    private lateinit var storeList: ArrayList<Store>
    private lateinit var goodsList: ArrayList<Goods>
    private lateinit var storeListFragment : StoreListFragment
    private lateinit var goodslistFragment : GoodsListFragment
    private lateinit var keyWord: String
    private lateinit var keyWordInput :EditText
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var test : TextView
    private val searchAddressCode = 10000
    private lateinit var addressString : String
    private val KakaoAPIKey = ""

    companion object{
       const val PERMISION_REQUEST_CODE = 1001

            const val DEFAULT_LAT :Double = 37.5740381
            const val DEFAULT_LNG :Double = 126.97458

            var userLat : Double = DEFAULT_LAT
            var userLng : Double = DEFAULT_LNG


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lm = getSystemService(Context.LOCATION_SERVICE)as LocationManager
        val isGPSEnabled: Boolean = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetWorkEnabled : Boolean = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 0)
        }else{
            when{
                isNetWorkEnabled -> {
                    val location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    userLat = location?.latitude!!
                    userLng = location?.longitude!!
                }
            }
        }


        if(checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {

            val location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if( location!=null ) {
                userLat = location.latitude
                userLng = location.longitude
            }
        }


        val store_detail = findViewById<TextView>(R.id.store_detail)
        store_detail.setOnClickListener(this)

        val storeDetail = findViewById<TextView>(R.id.store_detail)
        storeDetail.setOnClickListener(this)
        /*
        val myRef = database.getReference()
        myRef.child("message").push().setValue("hi")

        val msg = database.reference.child("Stores")

        test = findViewById(R.id.test)
        test.setOnClickListener(this)

        msg.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(data in dataSnapshot.children){
//                    var value = data.getValue()
//                    test.text = value.toString()
                    var value = data.value
                    test.text = value.toString()

                }
//                test.text=test_array[0]
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })*/


        setAddress = findViewById<TextView>(R.id.setAddressMainText)
        setAddress.setOnClickListener(this)
        searchBtn = findViewById<ImageButton>(R.id.searchBtn)
        searchBtn.setOnClickListener(this)
        keyWordInput =findViewById(R.id.key_word)

//        test = findViewById<TextView>(R.id.test)
//        test.setOnClickListener(this)
//
//        var test22 = findViewById<TextView>(R.id.test22)
//        test22.setOnClickListener(this)

        val navigationBar = findViewById<BottomNavigationView>(R.id.main_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        /*navigationBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home->
                R.id.navigation_favorite->
                R.id.navigation_log->
            }
        }*/

        // Store 목록 조회
//        val database : FirebaseDatabase = FirebaseDatabase.getInstance() //데이터베이스 부르기
//        val Stores = database.getReference().child("Stores") //Store 테이블에 접근
//        Stores.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                var i=0
//                var storeN: String
//                var storeImg :String
//                for(data in dataSnapshot.children){
//                    var map =data.value as Map<String,Any>
//                    storeN = map["storeName"].toString()
//                    storeImg = map["storeImgurl"].toString()
//                    var url = URL(storeImg)
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//            }
//        })


    }


    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
//                R.id.test22 -> {
//                    val intent = Intent(this, ManagerActivity::class.java)
//                    startActivity(intent)
//                }

                //R.id.searchBtn
                R.id.setAddressMainText -> {
                    val intent = Intent(this@MainActivity, MapViewActivity::class.java)
                    startActivityForResult(intent, searchAddressCode)
                }
                R.id.searchBtn -> {
                    keyWord = keyWordInput.text.toString()
                    if (keyWord != null) {
                        val bundle: Bundle = Bundle()
                        bundle.putString("keyword", keyWord)
                        //StoreFragment start
                        storeListFragment = StoreListFragment();
                        storeListFragment.arguments = bundle
                        supportFragmentManager.beginTransaction().replace(R.id.main_store_fragment, storeListFragment).commit();
                        //GoodsFragment start
                        goodslistFragment = GoodsListFragment();
                        goodslistFragment.arguments = bundle
                        supportFragmentManager.beginTransaction().replace(R.id.main_goods_fragment, goodslistFragment).commit();
                    }
                }
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            searchAddressCode -> {
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        addressString = data.extras?.getString("data").toString()
                    }
                    var mainAddrText: TextView = findViewById(R.id.setAddressMainText)
                    Log.d("main address:", addressString)
                    if (data != null)
                        mainAddrText.text = addressString

                }

            }
        }
    }

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
            R.id.navigation_log -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        false
    }
}
