package com.rocket.simpleweather.weather_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class WeatherSysData (
    @SerializedName("country")
    @Expose
    val countryCode: String,
    @SerializedName("sunrise")
    @Expose
    val sunriseTime: Long,
    @SerializedName("sunset")
    @Expose
    val sunsetTime: Long
)