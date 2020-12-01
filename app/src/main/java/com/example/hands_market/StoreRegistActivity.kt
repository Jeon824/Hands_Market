package com.example.hands_market

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.hands_market.MainActivity.Companion.DEFAULT_LAT
import com.example.hands_market.MainActivity.Companion.DEFAULT_LNG
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*



//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase

//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase

class StoreRegistActivity : AppCompatActivity(), View.OnClickListener {

    val Gallery = 0

    private val queryUrl :String = "http://www.inspond.com/daum.html"
    private lateinit var marketNameInput :EditText
    private lateinit var showImgInput :ImageView
    private lateinit var showLayoutImg :ImageView
    private lateinit var showInput :ImageView
    private lateinit var dataUri : Uri
    private lateinit var dataImgUri : Uri
    private lateinit var dataLayoutUri : Uri
//    lateinit var url :String
    var url:String ="0"
    var urlLayout:String ="0"
    lateinit var whatBtn :String //url과 layout중 어떤 것을 선택?
//    lateinit var url :String
    private lateinit var addressMainEditText: EditText
    private lateinit var mainAddress:String
    private lateinit var addressDetail:String
    private lateinit var webView : WebView
    private lateinit var puW: PopupWindow
    private lateinit var dismissPwBtn : ImageButton
    private lateinit var applyBtn : Button


    //private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_regist)

        val storeCreateBtn = findViewById<TextView>(R.id.storeCreateBtn)
        storeCreateBtn.setOnClickListener(this)

        marketNameInput = findViewById(R.id.market_name)
        showImgInput = findViewById<ImageView>(R.id.showImg)
        showLayoutImg = findViewById<ImageView>(R.id.showLayoutImg)




        addressMainEditText = findViewById(R.id.register_main_address)
        val storeRegSearchAddressBtn = findViewById<TextView>(R.id.storeRegSearchAddressBtn)
        storeRegSearchAddressBtn.setOnClickListener(this)

        val storeRegImgBtn : Button = findViewById(R.id.storeRegImgBtn)
        storeRegImgBtn.setOnClickListener{storeRegImg(showImgInput)}

        val storeRegStoreLayoutImgBtn : Button = findViewById(R.id.storeRegStoreLayoutImgBtn)
        storeRegStoreLayoutImgBtn.setOnClickListener{storeRegStoreLayoutImg(showLayoutImg)}

        // bottom navigation 선언
        val storeRegist_navigation = findViewById<BottomNavigationView>(R.id.storeRegist_navigation)
        storeRegist_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                //R.id.searchBtn
                R.id.storeCreateBtn -> {
                    Log.d("StoreDetailActivity", "-----------")
                    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    Log.d("StoreDetailActivity", "0000----")
                    var myRef = database.getReference()
                    Log.d("StoreDetailActivity~~~~~", "$url")
                    val StoreOne = Store(
                            storeName = marketNameInput.text.toString(),
                            managerID = "1",
                            storeAddress = "2",
                            storeLat = DEFAULT_LAT,
                            storeLng = DEFAULT_LNG,
                            storeImgurl=url,
                            SID = "0",
                            storeLayoutUrl="0"
                    )
                    Log.d("StoreDetailActivity", "2---------")
                    myRef.child("Stores").push().setValue(StoreOne)
                    Log.d("StoreDetailActivity", "3---------")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
//                R.id.showImg->{
//                    showInput=showImgInput
//                    storeRegImg()
//                }
                R.id.storeRegSearchAddressBtn->{
                    val inflater: LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val pw: View = inflater.inflate(R.layout.address_search, null)
                    webView = pw.findViewById<WebView>(R.id.address_web)
                    initWebView()
                    var width: Int = LinearLayout.LayoutParams.MATCH_PARENT
                    var height: Int = LinearLayout.LayoutParams.MATCH_PARENT
                    var focusable: Boolean = true

                    puW = PopupWindow(pw, width, height, focusable)

                    dismissPwBtn = pw.findViewById<ImageButton>(R.id.dismiss)
                    applyBtn = pw.findViewById(R.id.apply_address)
                    applyBtn.setOnClickListener(this)
                    dismissPwBtn.setOnClickListener(this)
                    puW.isTouchable = true
                    puW.contentView = pw
                    puW.showAtLocation(v, Gravity.CENTER, 0, 0)

                }
                R.id.apply_address->{
                    addressMainEditText.setText(mainAddress)
                }
                R.id.dismiss->{
                    puW.dismiss()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initWebView() {
        // JavaScript 허용


        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.allowFileAccess = false
        webView.settings.setAppCacheEnabled(false)
        webView.settings.domStorageEnabled = true
        webView.addJavascriptInterface(MyJavaScriptInterface(), "Android");
        webView.webViewClient = object : WebViewClient() {


            override fun onPageFinished(view: WebView, url: String) {
                webView.loadUrl("javascript:sample2_execDaumPostcode();")

            }



        }


        webView.loadUrl(queryUrl);
    }

    inner class MyJavaScriptInterface {
        @JavascriptInterface
        fun processDATA(data: String) {

            mainAddress = data

            /*var extra: Bundle = Bundle();
            var intent: Intent = Intent();
            Log.d("map address:", data)
            extra.putString("data", data);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);*/


            /*
            val mGeocoder : Geocoder = Geocoder(applicationContext)
            //geoCoder
            try {
                var resultLocation : List<Address> = mGeocoder.getFromLocationName("대전 동구 판교2길 7",1)
                Log.d("converted X:",resultLocation.get(0).latitude as String)
                Log.d("converted Y:",resultLocation.get(0).longitude as String)
            }
            catch (e: IOException) {
                Log.d("convert status :","fail")
            }*/

        }
    }

    private fun storeRegImg(showImgInput: ImageView) {
        showInput=showImgInput
        whatBtn="Img"
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery)
    }

    private fun storeRegStoreLayoutImg(showLayoutImg: ImageView) {
        showInput=showLayoutImg
        whatBtn="Layout"
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
//                                if( whatBtn=="Img"){
                                url=it.toString()
//                                }
//                                else{
//                                    urlLayout=it.toString()
//                                }

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
