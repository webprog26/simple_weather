package com.rocket.simpleweather

import android.util.Log

object Logger {

    private const val TAG = "simple_weather_tag"

    fun log(message: String) {
        Log.i(TAG, message)
    }
}