package com.rocket.simpleweather.weather_data.weaher_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rocket.simpleweather.weather_data.WeatherData
import com.rocket.simpleweather.weather_data.weaher_db.type_converters.WeatherDataTypeConverter

@Database(entities = [WeatherData::class], version = 1, exportSchema = false)
@TypeConverters(WeatherDataTypeConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao

    companion object {

        @Volatile private var instance: WeatherDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            WeatherDatabase::class.java, "weather.db")
            .build()
    }
}