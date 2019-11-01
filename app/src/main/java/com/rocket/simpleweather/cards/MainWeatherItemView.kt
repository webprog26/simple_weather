package com.rocket.simpleweather.cards

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.rocket.simpleweather.Logger
import com.rocket.simpleweather.R
import com.rocket.simpleweather.weather_data.WeatherData
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class MainWeatherItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AbstractWeatherItemView(context, attrs, defStyleAttr) {

    override fun getLayoutResId(): Int {
        return R.layout.main_weather_item_view_layout
    }

    override fun parseWeatherData(weatherData: WeatherData) {
        val weatherDescriptionData = weatherData.weatherDescriptionDataList[0]

        findViewById<TextView>(R.id.tv_temp_current).text = context.getString(R.string.current_temp,
            Math.round(weatherData.mainWeatherData.temp))
        findViewById<TextView>(R.id.tv_city_name).text = weatherData.cityName
        findViewById<TextView>(R.id.tv_temp_min_and_max).text = context.getString(R.string.min_and_max_temp,
            Math.round(weatherData.mainWeatherData.temp_min), Math.round(weatherData.mainWeatherData.temp_max))
        findViewById<TextView>(R.id.tv_main_description).text = weatherDescriptionData.weatherMain
        Picasso.get()
            .load("http://openweathermap.org/img/wn/${weatherDescriptionData.weatherIcon}@2x.png")
            .fit().into(findViewById<ImageView>(R.id.iv_weather_icon), object: Callback {
                override fun onSuccess() {
                    Logger.log("icon loaded successfully")
                }

                override fun onError(e: Exception?) {
                    Logger.log("icon loading error $e")
                }
            })
        findViewById<TextView>(R.id.tv_last_updated).text = context.getString(R.string.last_updated,
            weatherData.timeUpdated)
    }
}