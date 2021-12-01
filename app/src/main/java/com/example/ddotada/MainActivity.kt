package com.example.ddotada

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class MainActivity : AppCompatActivity(){

    lateinit var drawerLayout: DrawerLayout
    val PERMISSIONS_REQUEST_CODE = 100
    var REQUIRED_PERMISSIONS = arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION)
    var uLatitude:Double = 0.0
    var uLongitude:Double = 0.0
    val gpsmarker = MapPOIItem()

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
                    Log.e("LOCATION_ERROR", e.toString())
                    ActivityCompat.finishAffinity(this)

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    System.exit(0)
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
            gpsmarker.markerType = MapPOIItem.MarkerType.BluePin
            gpsmarker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
            mapView.addPOIItem(gpsmarker)
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
    }


}


