package com.example.hands_market

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
//import com.google.firebase.ktx.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.net.URL
//import com.google.firebase.database.ktx.database
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.math.log

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                    val intent = Intent(this, MapViewActivity::class.java)
                    startActivity(intent)
                }
                R.id.searchBtn -> {
                    keyWord = keyWordInput.text.toString()
                    if(keyWord!=null) {
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
            R.id.navigation_log -> {
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }
        false
    }
}
