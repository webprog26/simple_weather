package com.rocket.simpleweather.cards

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.rocket.simpleweather.Logger
import com.rocket.simpleweather.R
import com.rocket.simpleweather.weather_data.WeatherData
import kotlinx.android.synthetic.main.card_title_layout.view.*

abstract class AbstractWeatherItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    fun onWeatherViewAdded() {
        LayoutInflater.from(context)
            .inflate(
                getLayoutResId(),
                this,
                true
            )

        setPadding(16, 16, 16, 16)

        val titleResId = getTitleResId()

        if (titleResId != 0) {
            val titleView = findViewById<View>(getTitleViewResId()) as TextView
            titleView.text = context.getString(titleResId)
        }
    }

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    abstract fun parseWeatherData(weatherData: WeatherData)

    @StringRes
    open fun getTitleResId(): Int {
        return 0
    }

    private fun getTitleViewResId(): Int {
        return R.id.tv_card_title
    }
}