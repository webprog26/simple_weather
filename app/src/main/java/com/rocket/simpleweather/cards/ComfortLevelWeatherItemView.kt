package com.rocket.simpleweather.cards

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import android.widget.TextView
import com.rocket.simpleweather.R
import com.rocket.simpleweather.weather_data.WeatherData

class ComfortLevelWeatherItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AbstractWeatherItemView(context, attrs, defStyleAttr) {

    override fun getLayoutResId(): Int {
        return R.layout.comfort_level_weather_item_view_layout
    }

    override fun parseWeatherData(weatherData: WeatherData) {
        val humidity = weatherData.mainWeatherData.humidity
        findViewById<TextView>(R.id.tv_humidity).text = context.getString(R.string.humidity, humidity)
        findViewById<ProgressBar>(R.id.pb_humidity)
            .progress = humidity
        findViewById<TextView>(R.id.tv_pressure).text = context.getString(R.string.pressure, Math.round(weatherData.mainWeatherData.pressure))
    }

    override fun getTitleResId(): Int {
        return R.string.comfort_level_card_title
    }
}