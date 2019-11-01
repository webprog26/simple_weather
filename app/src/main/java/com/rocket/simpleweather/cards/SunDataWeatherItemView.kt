package com.rocket.simpleweather.cards

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.rocket.simpleweather.R
import com.rocket.simpleweather.weather_data.WeatherData
import java.text.SimpleDateFormat
import java.util.*

class SunDataWeatherItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AbstractWeatherItemView(context, attrs, defStyleAttr) {

    override fun getLayoutResId(): Int {
        return R.layout.sun_data_weather_item_view_layout
    }

    override fun parseWeatherData(weatherData: WeatherData) {
        findViewById<TextView>(R.id.tv_sunrise_time).text =
            context.getString(R.string.sunrise_time, getFormattedTime(weatherData.sysData.sunriseTime))
        findViewById<TextView>(R.id.tv_sunset_time).text =
            context.getString(R.string.sunset_time, getFormattedTime(weatherData.sysData.sunsetTime))
    }

    override fun getTitleResId(): Int {
        return R.string.sun_data
    }

    private fun getFormattedTime(timeInMillis: Long): String {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.timeInMillis = timeInMillis
        return SimpleDateFormat("HH:mm",
            Locale.getDefault()).format(calendar.time)

    }
}