package com.rocket.simpleweather

import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.rocket.simpleweather.ext_utils.toastLong

class MainActivity : AppCompatActivity() {

    private val ACCESS_COARSE_LOCATION_PERMISSION_REQUEST_CODE = 0
    private val USER_LAT = "user_lat"
    private val USER_LON = "user_lon"

    private lateinit var mFusedLocationProvider: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                    ACCESS_COARSE_LOCATION_PERMISSION_REQUEST_CODE)

            } else {
                initLocationFetching()
            }
        } else {
            initLocationFetching()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            ACCESS_COARSE_LOCATION_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    initLocationFetching()
                } else {
                   toastLong(getString(R.string.on_location_permission_denied_message))
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {

            }
        }

    }

    private fun initLocationFetching() {
        try {
            mFusedLocationProvider.lastLocation
                .addOnSuccessListener { location: Location? ->
                   when (location != null) {
                        true -> proceedWithLocation(location)
                        false -> toastLong(getString(R.string.on_location_permission_denied_message))

                    }
                }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun proceedWithLocation(location: Location) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)

        val latToString = location.latitude.toString()
        val lonToString = location.longitude.toString()

        val lastKnownLat = sharedPrefs.getString(USER_LAT, "")
        val lastKnownLon = sharedPrefs.getString(USER_LON, "")

        if (latToString.equals(lastKnownLat) && lonToString.equals(lastKnownLon)) {
            Logger.log("the same location lat: $latToString lon: $lonToString")
        } else {
            Logger.log("different location lat: $latToString lon: $lonToString")
            sharedPrefs.edit().putString(USER_LAT, latToString)
                .putString(USER_LON, lonToString).apply()
        }
    }
}
