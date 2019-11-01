package com.rocket.simpleweather.leafs

import android.content.Context
import com.rocket.simpleweather.cards.AbstractWeatherItemView
import com.rocket.simpleweather.weather_data.WeatherData

abstract class AbstractWeatherLeaf constructor(val data: WeatherData){
    fun getWeatherItemView(context: Context): AbstractWeatherItemView {
        val itemView = getWeatherItemViewInternal(context)
        itemView.onWeatherViewAdded()
        return itemView
    }

    protected abstract fun getWeatherItemViewInternal(context: Context): AbstractWeatherItemView
}