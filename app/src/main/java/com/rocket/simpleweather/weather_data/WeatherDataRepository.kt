package com.rocket.simpleweather.weather_data

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rocket.simpleweather.App
import com.rocket.simpleweather.Logger
import com.rocket.simpleweather.PreferencesStorage
import com.rocket.simpleweather.R
import com.rocket.simpleweather.network.WeatherNetworkService
import com.rocket.simpleweather.weather_data.weaher_db.WeatherDao
import com.rocket.simpleweather.weather_data.weaher_db.WeatherDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WeatherDataRepository {

    private val dataBase: WeatherDatabase = Room.databaseBuilder(
        App.instance,
        WeatherDatabase::class.java, "weather.db"
    ).build()

    fun getWeatherData(): LiveData<WeatherData> {
        refeshWeatherForecast()
        return dataBase.getWeatherDao().load()
    }

     fun refeshWeatherForecast() {
        val prefsStorage = PreferencesStorage
        val lat = prefsStorage.getLat()
        val lon = prefsStorage.getLon()
        if (lat != null && lon != null) {
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
                            Logger.log("response body: $data")
                            PushWeatherDataTask(dataBase.getWeatherDao()).execute(data)
//                            dataBase.getWeatherDao().save(data)
                        }
                    }
                })
        }
    }

    class PushWeatherDataTask(val dao: WeatherDao): AsyncTask<WeatherData, Unit, Unit>() {

        override fun doInBackground(vararg params: WeatherData?) {
            val data = params[0]
            if (data != null) {
                dao.save(data)
            }
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Logger.log("data saved")
        }
    }
}