package com.example.ddotada

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.PinnedPositions.pin
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import com.google.firebase.database.*
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import android.widget.TextView
import java.util.zip.Inflater



//,MapView.POIItemEventListener
class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    val PERMISSIONS_REQUEST_CODE = 100
    var REQUIRED_PERMISSIONS = arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION)
    var uLatitude:Double = 0.0
    var uLongitude:Double = 0.0
    val gpsmarker = MapPOIItem()
    val marker = MapPOIItem()


    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = firebaseDatabase.getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navi)

        val mapView = MapView(this)
        val map_view = findViewById<View>(R.id.map_View) as RelativeLayout
        val mapViewContainer = map_view
        mapViewContainer.addView(mapView)

        val permissionCheck = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            val lm: LocationManager =
                getSystemService(Context.LOCATION_SERVICE) as LocationManager
            try {
                val userNowLocation: Location =
                    lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!
                uLatitude = userNowLocation.latitude
                uLongitude = userNowLocation.longitude
                mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude), true)

            } catch (e: NullPointerException) {
                Log.e("LOCATION_ERROR", e.toString())

            }

        } else {
            Toast.makeText(this, "위치 권한이 없습니다.", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                PERMISSIONS_REQUEST_CODE
            )
        }


        val btn = findViewById<ImageButton>(R.id.btn_menu)
        drawerLayout = findViewById(R.id.main_drawer_layout)
        btn.setOnClickListener {
            drawerLayout.openDrawer((GravityCompat.START))
        }

        val gps = findViewById<ImageButton>(R.id.btn_gps)
        gps.setOnClickListener {
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                val lm: LocationManager =
                    getSystemService(Context.LOCATION_SERVICE) as LocationManager
                try {
                    val userNowLocation: Location =
                        lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!
                    uLatitude = userNowLocation.latitude
                    uLongitude = userNowLocation.longitude
                } catch (e: NullPointerException) {

                }

            } else {
                Toast.makeText(this, "위치 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(
                    this,
                    REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE
                )
            }
            mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude), true)
            gpsmarker.mapPoint = MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude)
            gpsmarker.itemName= "현재위치"
            gpsmarker.tag = 0
            gpsmarker.markerType = MapPOIItem.MarkerType.CustomImage
            gpsmarker.customImageResourceId = R.drawable.pins_gps
            gpsmarker.isCustomImageAutoscale= false
            gpsmarker.setCustomImageAnchor(0.5f,1.0f)
            mapView.addPOIItem(gpsmarker)
        }

        val btn_bikep = findViewById<ImageButton>(R.id.btn_bike)
        btn_bikep.setOnClickListener {
            val lm: LocationManager =
                getSystemService(Context.LOCATION_SERVICE) as LocationManager
            try {
                val userNowLocation: Location =
                    lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!
                uLatitude = userNowLocation.latitude
                uLongitude = userNowLocation.longitude


            } catch (e: NullPointerException) {

            }
            marker.mapPoint = MapPoint.mapPointWithGeoCoord(uLatitude+0.001, uLongitude-0.0008)
            marker.itemName= "현재위치"
            marker.tag = 1
            marker.markerType = MapPOIItem.MarkerType.CustomImage
            marker.customImageResourceId = R.drawable.pins_map
            marker.isCustomImageAutoscale= false
            marker.setCustomImageAnchor(0.5f,1.0f)
            mapView.addPOIItem(marker)
        }

        val text_user = findViewById<TextView>(R.id.text_user)
        try {
            databaseReference.child("User").child("admin").child("nickname").get().addOnSuccessListener{
                text_user.text = "N.Hoody"

            }.addOnFailureListener {
                Log.d("fail","못가져옴")
                text_user.text = "N.Hoody"
            }

        }catch (e:Exception){

        }

        val btn_bike = findViewById<Button>(R.id.btn_mybike)
        val btn_list = findViewById<Button>(R.id.btn_list)
        val btn_coin = findViewById<Button>(R.id.btn_coin)
        val btn_card = findViewById<Button>(R.id.btn_card)
        val btn_setting = findViewById<ImageButton>(R.id.btn_setting)
        val btn_filter = findViewById<ImageButton>(R.id.btn_filter)
        val filterlayout = findViewById<ConstraintLayout>(R.id.fit)
        btn_filter.setOnClickListener {
            if(filterlayout.isVisible == false){
                filterlayout.visibility = View.VISIBLE
            }else{
                filterlayout.visibility = View.INVISIBLE
            }
        }
        btn_bike.setOnClickListener {
            val intent = Intent(this, BikeregistActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
        btn_list.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
        btn_coin.setOnClickListener {
            val intent = Intent(this, HoodyActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
        btn_card.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
        btn_setting.setOnClickListener {
            val intent = Intent(this, UserSettingActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
        val balloon : ImageView = findViewById(R.id.balloon)
        val hiden = findViewById<Button>(R.id.hiden)
        hiden.setOnClickListener {
            if(balloon.isVisible == false){
                balloon.visibility = View.VISIBLE
            }else{
                balloon.visibility = View.INVISIBLE
            }
        }
    }



}


