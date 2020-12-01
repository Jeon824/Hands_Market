package com.example.hands_market

//import android.webkit.WebView
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.http.SslError
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.hands_market.MainActivity.Companion.userLat
import com.example.hands_market.MainActivity.Companion.userLng
import com.google.android.gms.maps.model.LatLng
import com.google.gson.JsonParser
import com.naver.maps.map.util.FusedLocationSource
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapReverseGeoCoder
import net.daum.mf.map.api.MapView
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class MapViewActivity() : AppCompatActivity(),/*OnMapReadyCallback*/MapView.OpenAPIKeyAuthenticationResultListener,MapView.MapViewEventListener,
    MapView.POIItemEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener, View.OnClickListener {

    private lateinit var  mapViewContainer : ViewGroup
    private lateinit var locationSource: FusedLocationSource
    private lateinit var setBtn : Button
    private lateinit var addressIn : EditText
    private val queryUrl :String = "http://www.inspond.com/daum.html"
    private var isRunning : Boolean=true
    private lateinit var map : MapView
    private lateinit var webView : WebView
    private lateinit var  handler : Handler
    private lateinit var popUpBtn : Button
    private lateinit var finishBtn : Button
    private lateinit var puW: PopupWindow
    private lateinit var dismissPwBtn : ImageButton




    /*
    inner class ConvertAddressToGeo : AsyncTask<String, Long, LatLng>() {
        private val RESTAPIKEY : String = "7c3dc93f6709733258e735aacc25bffd"
        private lateinit var latLng: com.google.android.gms.maps.model.LatLng
        override fun doInBackground(vararg params: String?): LatLng {

                var sendAddress : String = java.net.URLEncoder.encode(params[0], "UTF-8")
                var url : String  = "https://dapi.kakao.com/v2/local/search/address.json?query=" + sendAddress
                var jsonString : String = String()
                var buf : String = ""
                var Url : URL = URL(url)

                val conn : HttpsURLConnection = Url.openConnection() as HttpsURLConnection;
                val auth : String = "KakaoAK" + RESTAPIKEY
                conn.requestMethod = "GET"
                conn.setRequestProperty("X-Requested-With", "curl")
                conn.setRequestProperty("Authorization", auth)

                val br = BufferedReader(InputStreamReader(conn.inputStream, "UTF-8"))
                while (br.readLine().also { buf = it } != null) {
                    jsonString += buf
                }
                val paser = JsonParser()

                val J: JSONObject = paser.parse(jsonString) as JSONObject
                val meta: JSONObject = J.get("meta") as JSONObject

                val data: JSONArray = J.get("documents") as JSONArray
                val size = meta.get("total_count") as Long
                Log.d("size확인:", "" + size)



                if (size > 0) {
                    val jsonX: JSONObject = data.get(0) as JSONObject
                    Log.d("X좌표:", jsonX.get("x").toString())
                    Log.d("Y좌표:", jsonX.get("y").toString())
                    latLng = LatLng(jsonX.get("x") as Double, jsonX.get("y") as Double)
                    Log.d("(x,y):", "" + jsonX.get("x").toString() + jsonX.get("y").toString())
                }



            return latLng
            
        }

        override fun onPostExecute(result: LatLng?) {
            super.onPostExecute(result)
        }

        override fun onProgressUpdate(vararg values: Long?) {
            super.onProgressUpdate(*values)
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)

        map = MapView(this);
        //
        val mapViewContainer :ViewGroup = findViewById(R.id.map)
        mapViewContainer.addView(map)
        map.setMapViewEventListener(this)
        map.setPOIItemEventListener(this)
        map.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(userLat, userLng), 3, true)


        handler = Handler();

        popUpBtn = findViewById(R.id.pop_up_addr_search)
        popUpBtn.setOnClickListener(this)
        finishBtn = findViewById(R.id.lat_lng_set_btn)
        finishBtn.setOnClickListener(this)


        /*
            val mGeocoder : Geocoder = Geocoder(applicationContext)
            //geoCoder
            try {
                var resultLocation : List<Address> = mGeocoder.getFromLocationName("대전 동구 판교2길 7",1)
                Log.d("converted X:","" +  resultLocation[0].latitude)
                Log.d("converted Y:","" + resultLocation[0].longitude)
            }
            catch (e: IOException) {
                Log.d("convert status :","fail")
            }*/

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
            var extra: Bundle = Bundle();
            var intent: Intent = Intent();
            Log.d("map address:", data)
            extra.putString("data", data);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);

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

                        puW = PopupWindow(pw, width, height, focusable)

                        dismissPwBtn = pw.findViewById<ImageButton>(R.id.dismiss)
                        dismissPwBtn.setOnClickListener(this)
                        puW.isTouchable = true
                        puW.contentView = pw
                        puW.showAtLocation(v, Gravity.CENTER, 0, 0)


                    }
                    R.id.lat_lng_set_btn -> {
                        //val task = ConvertAddressToGeo()
                        //task.execute()
                        finish()
                    }
                    R.id.dismiss -> {
                        puW.dismiss()
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

    override fun onMapViewInitialized(p0: MapView?) {

    }

    override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {

    }

    override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {

    }

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {

    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {

    }

    override fun onCalloutBalloonOfPOIItemTouched(
            p0: MapView?,
            p1: MapPOIItem?,
            p2: MapPOIItem.CalloutBalloonButtonType?
    ) {

    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {

    }

}
