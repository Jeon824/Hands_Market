package com.example.hands_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPoint

class MapViewActivity : AppCompatActivity() {
    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)

        val mapView = MapView(this)
        val mapViewContainer : ViewGroup = findViewById<ViewGroup>(R.id.map_view)
        mapViewContainer.addView(mapView)
    }
}