package com.rocket.simpleweather.leafs

import android.content.Context
import com.rocket.simpleweather.cards.AbstractWeatherItemView
import com.rocket.simpleweather.cards.ComfortLevelWeatherItemView
import com.rocket.simpleweather.weather_data.WeatherData

class ComfortLevelLeaf(data: WeatherData) : AbstractWeatherLeaf(data) {
    override fun getWeatherItemViewInternal(context: Context): AbstractWeatherItemView {
        return ComfortLevelWeatherItemView(context)
    }
}