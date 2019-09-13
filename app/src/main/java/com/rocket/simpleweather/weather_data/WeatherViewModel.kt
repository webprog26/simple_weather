package com.rocket.simpleweather.weather_data

import androidx.lifecycle.ViewModel

class WeatherViewModel: ViewModel() {

    val currentWeather = WeatherDataRepository.getWeatherData()
}