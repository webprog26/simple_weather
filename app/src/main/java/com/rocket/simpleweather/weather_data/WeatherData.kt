package com.rocket.simpleweather.weather_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherData (
    @SerializedName("weather")
    @Expose
    val weatherDescriptionDataList: ArrayList<WeatherDescriptionData>,
    @SerializedName("main")
    @Expose
    val mainWeatherData: MainWeatherData,
    @SerializedName("wind")
    @Expose
    val windData: WindData,
    @SerializedName("clouds")
    @Expose
    val cloudsData:	CloudsData,
    @SerializedName("dt")
    @Expose
    val dataCalculationTime: Long,
    @SerializedName("sys")
    @Expose
    val sysData: WeatherSysData,
    @SerializedName("timezone")
    @Expose
    val timezone: Long,
    @SerializedName("id")
    @Expose
    val cityId: Long,
    @SerializedName("name")
    @Expose
    val cityName: String
)