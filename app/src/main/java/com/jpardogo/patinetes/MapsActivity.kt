package com.jpardogo.patinetes

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fondesa.kpermissions.extension.listeners
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                // Update UI with location data
                updateUI(location)
            }
        }
    }
    val locationRequest = LocationRequest()
        .setInterval(5000)
        .setFastestInterval(1000)
        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun updateUI(location: Location) {
        Toast.makeText(
            this@MapsActivity,
            "location - lat:${location.latitude}, lng: ${location.longitude}",
            Toast.LENGTH_SHORT
        ).show()
        // Add a marker in Sydney and move the camera
        //Vienna 48.2082° N, 16.3738° E
        val latLng = LatLng(location.latitude, location.longitude)
        mMap.addMarker(MarkerOptions().position(latLng).title("Current location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
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
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@MapsActivity)
                fusedLocationClient?.lastLocation
                    ?.addOnSuccessListener { location: Location? ->
                        location?.let {
                            updateUI(it)
                        } ?: startLocationUpdates()
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

    private fun startLocationUpdates() {
        val locationSettingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .setAlwaysShow(true)
        LocationServices.getSettingsClient(this)
            .checkLocationSettings(locationSettingsRequest.build())
            .addOnSuccessListener { response ->
                fusedLocationClient?.requestLocationUpdates(locationRequest, locationCallback, null /* Looper */)
            }
            .addOnFailureListener { e ->
                if (e is ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        e.startResolutionForResult(
                            this@MapsActivity,
                            REQUEST_CHECK_SETTINGS
                        )
                    } catch (sendEx: IntentSender.SendIntentException) {
                        // Ignore the error.
                    }

                }
            }

        Toast.makeText(this@MapsActivity, "startLocationUpdates.", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CHECK_SETTINGS -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        // All required changes were successfully made
                        Toast
                            .makeText(
                                this@MapsActivity,
                                "Success ${LocationSettingsStates.fromIntent(data).isLocationPresent}",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                        fusedLocationClient?.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            null /* Looper */
                        )
                    }
                    Activity.RESULT_CANCELED ->
                        // The user was asked to change settings, but chose not to
                        Toast.makeText(
                            this@MapsActivity,
                            "GPS activation canceled, no location found",
                            Toast.LENGTH_SHORT
                        ).show()
                    else -> {
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun stopLocationUpdates() {
        fusedLocationClient?.removeLocationUpdates(locationCallback)
    }

    companion object {
        const val REQUEST_CHECK_SETTINGS: Int = 532
    }
}
