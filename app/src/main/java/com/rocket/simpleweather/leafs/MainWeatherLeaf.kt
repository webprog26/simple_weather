package com.rocket.simpleweather.leafs

import android.content.Context
import com.rocket.simpleweather.cards.AbstractWeatherItemView
import com.rocket.simpleweather.cards.MainWeatherItemView
import com.rocket.simpleweather.weather_data.WeatherData

class MainWeatherLeaf (data: WeatherData): AbstractWeatherLeaf(data) {
    override fun getWeatherItemViewInternal(context: Context): AbstractWeatherItemView {
        return MainWeatherItemView(context)
    }
}