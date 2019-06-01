package com.example.metroparkparkingpill

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.*


class MapOverviewActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_overview)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onMarkerClick(p0: Marker?) = false

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        setUpMap() // Setup the map settings
        addGraphics() // add graphics to the map
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private fun setUpMap() {

        // check if the user have given permission to access gps data, else request for access
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

            // navigate to the users position on the map
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }

    fun goToParkingInfo(view: View) {
        val intent = Intent(this, ParkingAreaListActivity::class.java)
        startActivity(intent)
    }

    private fun addGraphics() {

        val positionData = DataStorage.getInstance(this).getData()

        positionData.parkingAreas.forEach { area ->

            // all positions in area
            val posMarkerArea: ArrayList<LatLng> = ArrayList()
            var polyoption = PolygonOptions()
            area.position.forEach { pos ->
                val marker = LatLng(pos.markerlat, pos.markerlong)
                posMarkerArea.add(marker)
            }
            posMarkerArea.forEach { position ->
                polyoption.add(position)

            }

            // all spaces
            val posMarkerSpace: ArrayList<MarkerOptions> = ArrayList()
            area.parkingSpaceList.forEach { space ->

                val marker = LatLng(space.position.markerlat, space.position.markerlong)
                val markeroption = MarkerOptions().position(marker).title("Space ID: " + space.parkingSpaceName)
                    .snippet("Allowed Parking Time: " + space.allowedParkingTime.toString() + " min.")

                // make a orange marker
                if (space.occupied) {
                    markeroption.icon(
                        BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    )

                    val currentTime = Date()
                    val currentTimeMil = currentTime.time

                    val allowedParkTimeMil = space.allowedParkingTime * 60000

                    // make a red marker
                    if (currentTimeMil > allowedParkTimeMil + space.arrivalTime) {
                        markeroption.icon(
                            BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_RED)
                        )
                    }
                } else {
                    // make a green marker
                    markeroption.icon(
                        BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                    )
                }
                posMarkerSpace.add(markeroption)
            }

            posMarkerSpace.forEach { marker ->
                mMap.addMarker(marker)
            }
            mMap.addPolygon(polyoption.clickable(true))

        }
    }
}
