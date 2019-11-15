package com.rocket.simpleweather.weather_data.weaher_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rocket.simpleweather.weather_data.WeatherData
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(data: WeatherData): Completable

    @Query("SELECT * FROM weatherdata")
    fun load(): Maybe<WeatherData>

}