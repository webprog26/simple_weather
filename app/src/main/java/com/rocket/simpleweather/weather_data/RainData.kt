package com.rocket.simpleweather.weather_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RainData (
    @SerializedName("1h")
    @Expose
    val rain1h: Int,
    @SerializedName("3h")
    @Expose
    val rain3h: Int
)