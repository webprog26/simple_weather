package com.rocket.simpleweather.weather_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel: ViewModel() {

    fun getWeatherData(): LiveData<WeatherData?> {
        return  WeatherDataRepository.getWeatherData()
    }
}