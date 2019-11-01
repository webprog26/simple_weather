package com.rocket.simpleweather

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rocket.simpleweather.cards.AbstractWeatherItemView
import com.rocket.simpleweather.leafs.AbstractWeatherLeaf
import com.rocket.simpleweather.weather_data.WeatherData

class WeatherAdapter (var leafs: List<AbstractWeatherLeaf>)
    : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun getItemCount(): Int {
        return leafs.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        Logger.log("viewType $viewType")
        return WeatherViewHolder(leafs[viewType].getWeatherItemView(parent.context))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(leafs[position].data)
    }

    class WeatherViewHolder(private val view: AbstractWeatherItemView) : RecyclerView.ViewHolder(view) {

        fun bind(data: WeatherData) {
            Logger.log(view::class.java.name)
            view.parseWeatherData(data)
        }
    }

    fun updateWithWeargerData(leafs: List<AbstractWeatherLeaf>) {
        this.leafs = leafs
        notifyDataSetChanged()

    }
}