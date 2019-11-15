package com.rocket.simpleweather.weather_data

import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.rocket.simpleweather.App
import com.rocket.simpleweather.Logger
import com.rocket.simpleweather.PreferencesStorage
import com.rocket.simpleweather.R
import com.rocket.simpleweather.network.WeatherNetworkService
import com.rocket.simpleweather.weather_data.weaher_db.WeatherDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

object WeatherDataRepository {

    private val disposable = CompositeDisposable()


    private val dataBase: WeatherDatabase = Room.databaseBuilder(
        App.instance,
        WeatherDatabase::class.java, "weather.db"
    ).build()

    fun updateWeatherData(weatherData: MutableLiveData<WeatherData>) {
        Logger.log("updateWeatherData")
        refreshWeatherForecast(weatherData)
    }

     private fun refreshWeatherForecast(weatherData: MutableLiveData<WeatherData>) {
        val prefsStorage = PreferencesStorage
        val lat = prefsStorage.getLat()
        val lon = prefsStorage.getLon()
        if (lat != null && lon != null) {
            disposable.add(dataBase.getWeatherDao().load()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    Logger.log("loaded from the database")
                    weatherData.value = it
                }))
            WeatherNetworkService
                .getCurrentWeatherDataApi()
                .getCurrentWeatherData(
                    lat,
                    lon,
                    App.instance.getString(R.string.open_weather_api_key),
                    "metric"
                ).enqueue(object : Callback<WeatherData> {
                    override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                        Logger.log("request failed " + t.message)
                    }

                    override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                        Logger.log("response code is " + response.code())
                        val data = response.body()
                        if (data != null) {
                            val calendar = Calendar.getInstance()
                            calendar.timeZone = TimeZone.getDefault()
                            calendar.timeInMillis = System.currentTimeMillis()
                            data.timeUpdated =  SimpleDateFormat("HH:mm",
                                Locale.getDefault()).format(calendar.time)
                            Logger.log("response body: $data")
                            disposable.add(dataBase.getWeatherDao().save(data)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    Logger.log("saved to the database")
                                    weatherData.value = data
                                })
                        }
                    }
                })
        }
    }

    fun onCleared() {
        disposable.clear()
    }
}