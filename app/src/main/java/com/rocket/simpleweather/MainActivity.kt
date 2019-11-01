package com.rocket.simpleweather

import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.rocket.simpleweather.ext_utils.toastLong
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rocket.simpleweather.leafs.*
import com.rocket.simpleweather.weather_data.WeatherData
import com.rocket.simpleweather.weather_data.WeatherDataRepository
import com.rocket.simpleweather.weather_data.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private val ACCESS_COARSE_LOCATION_PERMISSION_REQUEST_CODE = 0


    private lateinit var mFusedLocationProvider: FusedLocationProviderClient

    private val rvCards by lazy { findViewById<RecyclerView>(R.id.rv_cards) }
    private val adapter by lazy { WeatherAdapter(emptyList<AbstractWeatherLeaf>()) }
    private lateinit var weatherModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.log("onCreate")
        setContentView(R.layout.activity_main)
        weatherModel = ViewModelProviders.of(this)[WeatherViewModel::class.java]
        mFusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        rvCards.layoutManager = LinearLayoutManager(this)
        rvCards.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        Logger.log("onResume")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                    ACCESS_COARSE_LOCATION_PERMISSION_REQUEST_CODE
                )

            } else {
                initLocationFetching()
            }
        } else {
            initLocationFetching()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
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
        Logger.log("initLocationFetching")
        try {
            mFusedLocationProvider.lastLocation
                .addOnSuccessListener { location: Location? ->
                    when (location != null) {

                        true -> proceedWithLocation(location)
                        false -> toastLong(getString(R.string.on_location_permission_denied_message))

                    }
                }.addOnFailureListener{
                    it.printStackTrace()
                }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun proceedWithLocation(location: Location) {
        Logger.log("proceedWithLocation()")

        val prefsStorage = PreferencesStorage

        val latToString = location.latitude.toString().substring(0, 6)
        val lonToString = location.longitude.toString().substring(0, 6)

        val lastKnownLat = prefsStorage.getLat()
        val lastKnownLon = prefsStorage.getLon()

        if (latToString.equals(lastKnownLat) && lonToString.equals(lastKnownLon)) {
            Logger.log("the same location lat: $latToString lon: $lonToString")
        } else {
            Logger.log("different location lat: $latToString lon: $lonToString")
            prefsStorage.saveCoordinates(latToString, lonToString)
        }

        weatherModel.getWeatherData().observe(this, Observer {
            Logger.log("observed: $it")
            if (it != null) {
                val leafs = arrayListOf(
                    MainWeatherLeaf(it),
                    ComfortLevelLeaf(it),
                    WindWeatherLeaf(it),
                    SunDataWeatherLeaf(it)
                )
                adapter.updateWithWeargerData(leafs)
            } else {
                Logger.log("weather data is null")
            }
        })
    }
}
