package com.rocket.simpleweather.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherNetworkService {

    private const val BASE_URL = "https://api.openweathermap.org"

    private val retrofit by lazy { Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build() }

    fun getCurrentWeatherDataApi(): CurrentWeatherDataApi {
        return retrofit.create(CurrentWeatherDataApi::class.java)
    }

}