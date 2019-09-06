package com.rocket.simpleweather

import android.content.SharedPreferences
import android.preference.PreferenceManager

object PreferencesStorage {

    private const val USER_LAT = "user_lat"
    private const val USER_LON = "user_lon"

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.instance)

    fun saveCoordinates(lat: String, lon: String) {
        prefs.edit()
            .putString(USER_LAT, lat)
            .putString(USER_LON, lon).apply()
    }

    fun getLat(): String? {
        return prefs.getString(USER_LAT, null)
    }

    fun getLon(): String? {
        return prefs.getString(USER_LON, null)
    }
}