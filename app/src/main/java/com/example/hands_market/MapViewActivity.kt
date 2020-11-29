package com.example.hands_market

//import com.naver.maps.map.*

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.naver.maps.map.util.FusedLocationSource
import net.daum.mf.map.api.MapReverseGeoCoder
import net.daum.mf.map.api.MapView
import kotlin.properties.Delegates


class MapViewActivity() : AppCompatActivity(),/*OnMapReadyCallback*/MapView.OpenAPIKeyAuthenticationResultListener, MapReverseGeoCoder.ReverseGeoCodingResultListener, View.OnClickListener {

    private lateinit var  mapViewContainer : ViewGroup
    private lateinit var locationSource: FusedLocationSource
    //private lateinit var mapFragment: MapFragment
    //private lateinit var naverMap: NaverMap
    private lateinit var setBtn : Button
    private lateinit var addressIn : EditText
    private val queryUrl :String = "http://127.0.0.1/daum.html"
    private var isRunning : Boolean=true
    private lateinit var map : MapView
    private lateinit var webView : WebView
    private lateinit var  handler : Handler
    private lateinit var popUpBtn : Button

    //private lateinit var ll : com.google.android.gms.maps.model.LatLng


    companion object{
        const val DEFAULT_LAT :Double = 37.5740381
        const val DEFAULT_LNG :Double = 126.97458

        var userLat by Delegates.notNull<Double>()
        var userLng by Delegates.notNull<Double>()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)



        map = MapView(this);
        val mapViewContainer :ViewGroup = findViewById(R.id.map)
        mapViewContainer.addView(map)

        handler = Handler();

        popUpBtn = findViewById(R.id.pop_up_addr_search)
        popUpBtn.setOnClickListener(this)


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

    @RequiresApi(Build.VERSION_CODES.O)
    fun initWebView() {
        // JavaScript 허용
        val dialog : Dialog = Dialog(this)
        webView.apply{

            webView.webChromeClient = WebChromeClient()
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    if (URLUtil.isNetworkUrl(url)) {
                        return false
                    }
                    if (appInstalledOrNot(url)) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    } else {
                        // do something if app is not installed
                    }
                    return true
                }
            }
        webView.getSettings().setJavaScriptEnabled(true);
        // JavaScript의 window.open 허용
            settings.javaScriptEnabled = true
            settings.setSupportMultipleWindows(true)
        webView.settings.javaScriptCanOpenWindowsAutomatically = true;
            webView.settings.databaseEnabled = false
            webView.settings.allowFileAccess = false
            webView.settings.setAppCacheEnabled(false)
            webView.settings.domStorageEnabled = true
        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        // 두 번째 파라미터는 사용될 php에도 동일하게 사용해야함
        webView.addJavascriptInterface(AndroidBridge(), "Android");
        // web client 를 chrome 으로 설정
            //webView.webChromeClient = WebChromeClient();
        }
        // webview url load
        webView.loadUrl(queryUrl);
    }

    private fun appInstalledOrNot(uri: String): Boolean {
        val pm: PackageManager = packageManager
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return false
    }

    inner class MyJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        fun processDATA(data: String) {
            var extra: Bundle = Bundle();
            var intent: Intent = Intent();
            extra.putString("data", data);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);
            finish();
        }
    }


        inner class AndroidBridge{
            @JavascriptInterface
            fun setAddress(arg1: String, arg2: String, arg3: String) {
                handler.post(Runnable() {
                    @Override
                    fun run() {
                        //result.setText(String.format("(%s) %s %s", arg1, arg2, arg3))
                        //WebView를 초기화 하지않으면 재사용할 수 없음
                    }
                })
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onClick(v: View?) {
            if (v != null) {
                when (v.id) {
                    R.id.pop_up_addr_search -> {

                        val inflater: LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                        val pw: View = inflater.inflate(R.layout.address_search, null)
                        webView = pw.findViewById<WebView>(R.id.address_web)
                        initWebView()
                        var width: Int = LinearLayout.LayoutParams.MATCH_PARENT
                        var height: Int = LinearLayout.LayoutParams.MATCH_PARENT
                        var focusable: Boolean = true

                        val puW: PopupWindow = PopupWindow(pw, width, height, focusable)
                        puW.contentView = pw

                        puW.showAtLocation(v, Gravity.CENTER, 0, 0)

                    }
                }
            }
        }


    override fun onDaumMapOpenAPIKeyAuthenticationResult(p0: MapView?, p1: Int, p2: String?) {
        TODO("Not yet implemented")
    }

    override fun onReverseGeoCoderFoundAddress(p0: MapReverseGeoCoder?, p1: String?) {
        TODO("Not yet implemented")
    }

    override fun onReverseGeoCoderFailedToFindAddress(p0: MapReverseGeoCoder?) {
        TODO("Not yet implemented")
    }

    inner class SslWebViewConnect : WebViewClient() {
        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            handler?.proceed()
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }
    }

}
