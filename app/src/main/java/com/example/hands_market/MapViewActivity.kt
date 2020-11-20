package com.example.hands_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPoint

class MapViewActivity : AppCompatActivity() {
    
    private lateinit var mapView: MapView
    private lateinit var  mapViewContainer : ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)

        mapView = MapView(this)
        mapViewContainer = findViewById<ViewGroup>(R.id.map_view)
        mapViewContainer.addView(mapView)
    }
}
