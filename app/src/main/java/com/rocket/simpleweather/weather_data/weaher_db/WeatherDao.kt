package com.rocket.simpleweather.weather_data.weaher_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rocket.simpleweather.weather_data.WeatherData

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(data: WeatherData)

    @Query("SELECT * FROM weatherdata")
    fun load(): LiveData<WeatherData?>

}