package com.example.ddotada

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.security.MessageDigest


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapView = MapView(this)
        val map_view = findViewById<View>(R.id.map_View) as RelativeLayout
        val mapViewContainer = map_view
        mapViewContainer.addView(mapView)
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(33.46194, 126.90892), true)
        mapView.setZoomLevel(6, true)

        val btn = findViewById<Button>(R.id.button5)
        btn.setOnClickListener {
            main_drawer_layout
        }

    }
}


