package com.rocket.simpleweather.weather_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CloudsData (
    @SerializedName("all")
    @Expose
    val all: Int
)