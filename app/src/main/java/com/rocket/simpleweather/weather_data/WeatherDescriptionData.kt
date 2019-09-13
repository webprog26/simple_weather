package com.rocket.simpleweather.weather_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class WeatherDescriptionData (
    @SerializedName("id")
    @Expose
    val weatherConditionId: Int,
    @SerializedName("main")
    @Expose
    val weatherMain: String,
    @SerializedName("description")
    @Expose
    val weatherDescription: String,
    @SerializedName("icon")
    @Expose
    val weatherIcon: String
)