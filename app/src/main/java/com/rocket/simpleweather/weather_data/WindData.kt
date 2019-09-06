package com.rocket.simpleweather.weather_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WindData (
    @SerializedName("speed")
    @Expose
    val windSpeed: Double,
    @SerializedName("deg")
    @Expose
    val windDirection: Double
)