package com.rocket.simpleweather.weather_data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel: ViewModel() {

    private val weatherData = MutableLiveData<WeatherData>()

    fun updateWeatherData(): MutableLiveData<WeatherData> {
        WeatherDataRepository.updateWeatherData(weatherData)
        return weatherData
    }

    override fun onCleared() {
        super.onCleared()
        WeatherDataRepository.onCleared()
    }
}