package com.rocket.simpleweather.cards

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.rocket.simpleweather.R
import com.rocket.simpleweather.weather_data.WeatherData

class WindWeatherItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AbstractWeatherItemView(context, attrs, defStyleAttr) {

    override fun getLayoutResId(): Int {
        return R.layout.wind_weather_item_view_layout
    }

    override fun parseWeatherData(weatherData: WeatherData) {
        val windDirection = getWindDirection(weatherData.windData.windDirection)

        if (windDirection != null) {
            findViewById<TextView>(R.id.tv_wind_direction).text =
                context.getString(R.string.wind_direction, windDirection)
        }
        findViewById<TextView>(R.id.tv_wind_speed).text =
            context.getString(R.string.wind_speed, Math.round(weatherData.windData.windSpeed))
    }

    override fun getTitleResId(): Int {
        return R.string.wind
    }

    private fun getWindDirection(windDirection: Double): String? {
        when (windDirection) {
            in 0.0..11.25 -> return "N"
            in 348.75..360.0 -> return "N"
            in 11.25..33.75 -> return "NNE"
            in 33.75..56.25 -> return "NE"
            in 56.25..78.75 -> return "ENE"
            in 78.75..101.25 -> return "E"
            in 101.25..123.75 -> return "ESE"
            in 123.75..146.25 -> return "SE"
            in 146.25..168.75 -> return "SSE"
            in 168.75..191.25 -> return "S"
            in 191.25..213.75 -> return "SSW"
            in 213.75..236.25 -> return "SW"
            in 236.25..258.75 -> return "WSW"
            in 258.75..281.25 -> return "W"
            in 281.25..303.75 -> return "WNW"
            in 303.75..326.25 -> return "NW"
            in 326.25..348.75 -> return "NNW"
            else -> return null
        }
    }
}