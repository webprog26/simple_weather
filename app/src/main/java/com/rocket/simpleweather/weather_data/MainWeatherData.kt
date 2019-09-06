package com.rocket.simpleweather.weather_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainWeatherData (
    @SerializedName("temp")
    @Expose
    val temp: Double,
    @SerializedName("pressure")
    @Expose
    val pressure: Double,
    @SerializedName("humidity")
    @Expose
    val humidity: Int
)