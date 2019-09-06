package com.rocket.simpleweather.network

import com.rocket.simpleweather.weather_data.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherDataApi {

    @GET("/data/2.5/weather")
    fun getCurrentWeatherData(@Query("lat") lat: String,
                              @Query("lon") lon: String,
                              @Query("appid") appid: String,
                              @Query("units") units: String): Call<WeatherData>
}