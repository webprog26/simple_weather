package com.rocket.simpleweather.weather_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rocket.simpleweather.App
import com.rocket.simpleweather.Logger
import com.rocket.simpleweather.PreferencesStorage
import com.rocket.simpleweather.R
import com.rocket.simpleweather.network.WeatherNetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel: ViewModel() {

    private val currentWeather: MutableLiveData<WeatherData>
    by lazy { MutableLiveData<WeatherData>().also {
        loadWeatherData()
    } }

    fun getWeatherData() : LiveData<WeatherData> {
        return currentWeather
    }

    private fun loadWeatherData() {
        val prefsStorage = PreferencesStorage
        val lat = prefsStorage.getLat()
        val lon = prefsStorage.getLon()

        if (lat != null && lon != null) {
            WeatherNetworkService
                .getCurrentWeatherDataApi()
                .getCurrentWeatherData(lat,
                    lon,
                    App.instance.getString(R.string.open_weather_api_key),
                    "metric").enqueue(object : Callback<WeatherData> {
                    override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                        Logger.log("response code is " + response.code())
                        currentWeather.postValue(response.body())
                    }
                })
        }

    }
}