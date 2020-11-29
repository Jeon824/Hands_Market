package com.example.hands_market

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.naver.maps.geometry.LatLng
//import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import net.daum.android.map.geocoding.ReverseGeoCodingWebService
import net.daum.mf.map.api.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MapViewActivity() : AppCompatActivity(), /*OnMapReadyCallback*/MapView.OpenAPIKeyAuthenticationResultListener, MapReverseGeoCoder.ReverseGeoCodingResultListener {

    private lateinit var  mapViewContainer : ViewGroup
    private lateinit var locationSource: FusedLocationSource
    //private lateinit var mapFragment: MapFragment
    //private lateinit var naverMap: NaverMap
    private lateinit var setBtn : Button
    private lateinit var addressIn : EditText
    private val queryUrl :String = "http://openapi.epost.go.kr/postal/retrieve"
    private var isRunning : Boolean=true
    private lateinit var map : MapView
    private lateinit var webView : WebView
    private lateinit var  handler : Handler

    //private lateinit var ll : com.google.android.gms.maps.model.LatLng


    companion object{
        const val DEFAULT_LAT :Double = 37.5740381
        const val DEFAULT_LNG :Double = 126.97458



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)
        map = MapView(this);
        val mapViewContainer :ViewGroup = findViewById(R.id.map)
        mapViewContainer.addView(map)

        handler = Handler();


        //var mapRevGCoder = MapReverseGeoCoder("37d359c49b925138434271d6bffa1686",)



        //map.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 9, true)
       /*NaverMap
        setBtn = findViewById(R.id.lat_lng_set_btn)
        addressIn = findViewById(R.id.address_input)


        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        setBtn.setOnClickListener {
            val address :String = addressIn.text.toString()
        }

        // bottom navigation 선언
        val mapView_navigation = findViewById<BottomNavigationView>(R.id.mapView_navigation)
        mapView_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)*/
    }/*NaverMap

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapReady(naverMap: NaverMap) {

        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true
        uiSettings.isZoomControlEnabled = true
        uiSettings.isZoomGesturesEnabled = true
        var camp : com.naver.maps.map.CameraPosition = CameraPosition(position,10.0)
        naverMap.cameraPosition = camp
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning=false
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        var position : LatLng = LatLng(37.570975,126.977759)
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
    }*/

    fun initWebView() {
        // WebView 설정
        webView = WebView(this)
        webView = findViewById(R.id.address_web);
        // JavaScript 허용
        webView.getSettings().setJavaScriptEnabled(true);
        // JavaScript의 window.open 허용
        webView.settings.javaScriptCanOpenWindowsAutomatically = true;
        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        // 두 번째 파라미터는 사용될 php에도 동일하게 사용해야함
        webView.addJavascriptInterface(AndroidBridge(), "TestApp");
        // web client 를 chrome 으로 설정
        webView.setWebChromeClient(WebChromeClient());
        // webview url load
        webView.loadUrl("http://codeman77.ivyro.net/getAddress.php");
    }

    override fun onDaumMapOpenAPIKeyAuthenticationResult(p0: MapView?, p1: Int, p2: String?) {

    }

    override fun onReverseGeoCoderFoundAddress(p0: MapReverseGeoCoder?, p1: String?) {

    }

    override fun onReverseGeoCoderFailedToFindAddress(p0: MapReverseGeoCoder?) {

    }

    inner class AndroidBridge {
        @JavascriptInterface
        fun setAddress(arg1 : String,arg2:String,arg3:String) {
            handler.post(Runnable() {
                @Override
                fun run() {
                    //result.setText(String.format("(%s) %s %s", arg1, arg2, arg3))
                    //WebView를 초기화 하지않으면 재사용할 수 없음
                    initWebView();
                }
            })
        }
    }


}
