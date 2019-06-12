package com.jpardogo.patinetes

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fondesa.kpermissions.extension.listeners
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Creates the request with the permissions you would like to request.
        val request = permissionsBuilder(Manifest.permission.ACCESS_FINE_LOCATION).build()
        // Send the request when you want.
        request.send()
        request.listeners {

            onAccepted { permissions ->
                // Notified when the permissions are accepted.
                //TODO go to location
                val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@MapsActivity)
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        location?.let {
                            // Add a marker in Sydney and move the camera
                            //Vienna 48.2082° N, 16.3738° E
                            val latLng = LatLng(location.latitude, location.longitude)
                            mMap.addMarker(MarkerOptions().position(latLng).title("Current location"))
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                        } ?: Toast.makeText(
                            this@MapsActivity,
                            "Last known location not found.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }

            onDenied { permissions ->
                // Notified when the permissions are denied.
                Toast.makeText(this@MapsActivity, "Permission denied.", Toast.LENGTH_LONG).show()
            }

            onPermanentlyDenied { permissions ->
                // Notified when the permissions are permanently denied.
                Toast.makeText(this@MapsActivity, "Permission permanently denied.", Toast.LENGTH_LONG).show()
            }

            onShouldShowRationale { permissions, nonce ->
                // Notified when the permissions should show a rationale.
                // The nonce can be used to request the permissions again.
                Toast.makeText(this@MapsActivity, "Should show rationale.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
