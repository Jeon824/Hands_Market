package com.example.hands_market

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import net.daum.mf.map.api.CameraPosition
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapReverseGeoCoder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MapViewActivity : AppCompatActivity() , OnMapReadyCallback{
    
    private lateinit var mapView: MapView
    private lateinit var  mapViewContainer : ViewGroup
    private lateinit var locationSource: FusedLocationSource
    private lateinit var mapFragment: MapFragment
    private lateinit var naverMap: NaverMap
    private lateinit var setBtn : Button
    private lateinit var addressIn : EditText
    private val queryUrl :String = "http://openapi.epost.go.kr/postal/retrieve"
    private var isRunning : Boolean=true
    private lateinit var ll : com.google.android.gms.maps.model.LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)

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
        mapView_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

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
    }
}
