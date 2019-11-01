package com.rocket.simpleweather.weather_data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class WeatherData (
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val cityId: Long,
    @SerializedName("weather")
    @Expose
    val weatherDescriptionDataList: List<WeatherDescriptionData>,
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
    val timezone: Int,
    @SerializedName("name")
    @Expose
    val cityName: String,
    var timeUpdated: String
)