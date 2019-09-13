package com.rocket.simpleweather.weather_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class SnowData (
    @SerializedName("1h")
    @Expose
    val snow1h: Int,
    @SerializedName("3h")
    @Expose
    val snow3h: Int
)