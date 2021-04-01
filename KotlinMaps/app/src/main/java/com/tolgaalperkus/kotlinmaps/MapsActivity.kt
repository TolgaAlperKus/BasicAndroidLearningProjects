package com.tolgaalperkus.kotlinmaps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var locationListener : LocationListener
    private lateinit var mMap: GoogleMap
    private lateinit var locationManager : LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(myListener)
            /*
        // Latitude & Longitude -> LatLng
        //52.363478 , 4.8808445 amsterdam
        val amsterdam = LatLng(52.363478, 4.8808445)
        mMap.addMarker(MarkerOptions().position(amsterdam).title("Marker in Amsterdam"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(amsterdam, 15f))
         */

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener =object : LocationListener{
            override fun onLocationChanged(location: Location) {
                mMap.clear()
                val userLocation = LatLng(location.latitude,location.longitude)
                mMap.addMarker(MarkerOptions().position(userLocation).title("Your Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15f))

                val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
                try {
                    val addressList = geocoder.getFromLocation(
                        location.latitude,location.longitude,1)
                    if(addressList != null && addressList.size > 0){
                        println(addressList[0].toString())
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                }


            }

        }

        if(ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
        else{
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,1,1f,locationListener)
            val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if(lastLocation != null){
                val lastKnownLatLng = LatLng(lastLocation.latitude,lastLocation.longitude)
                mMap.addMarker(MarkerOptions().position(lastKnownLatLng).title("You're Here"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnownLatLng,15f))
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){
            if(grantResults.isNotEmpty()){
                if(ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,1,1f, locationListener)
                    }
            }
        }
    }
    val myListener = object : GoogleMap.OnMapLongClickListener{
        override fun onMapLongClick(p0: LatLng?) {
            mMap.clear()
            var address = ""
            val geocoder = Geocoder(this@MapsActivity , Locale.getDefault())
            if(p0 !=null){
                try{

                    val addressList = geocoder.getFromLocation(p0.latitude,p0.longitude,1)
                    if(addressList.isNotEmpty()){
                        if(addressList[0].thoroughfare != null){
                            address += addressList[0].thoroughfare
                            if(addressList[0].subThoroughfare !=null){
                                address += addressList[0].subThoroughfare

                            }
                        }
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                }
                mMap.addMarker(MarkerOptions().position(p0).title(address))

            }else if (address.equals("Unnamed Road")){
                Toast.makeText(applicationContext, "You can't pick sea or deserts", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, "Try again", Toast.LENGTH_SHORT).show()
            }

        }

    }
}