package com.rocket.simpleweather.weather_data.weaher_db.type_converters

import androidx.room.TypeConverter
import com.rocket.simpleweather.weather_data.*
import org.json.JSONArray
import org.json.JSONObject

private const val WEATHER_DESCRIPTION_DATA_ID = "id"
private const val WEATHER_DESCRIPTION_DATA_MAIN = "main"
private const val WEATHER_DESCRIPTION_DATA_DESCRIPTION = "description"
private const val WEATHER_DESCRIPTION_DATA_ICON = "icon"

private const val MAIN_WEATHER_DATA_TEMP = "temp"
private const val MAIN_WEATHER_DATA_PRESSURE = "pressure"
private const val MAIN_WEATHER_DATA_HUMIDITY = "humidity"

private const val WIND_DATA_SPEED = "speed"
private const val WIND_DATA_DIRECTION = "direction"

private const val WEATHER_SYS_DATA_COUNTRY_CODE = "country_code"
private const val WEATHER_SYS_DATA_SUNRISE_TIME = "sunrise_time"
private const val WEATHER_SYS_DATA_SUNSET_TIME = "sunset_time"

private const val SNOW_DATA_ONE_HOUR = "snow_one_hour"
private const val SNOW_DATA_THREE_HOURS = "snow_three_hours"

private const val RAIN_DATA_ONE_HOUR = "rain_one_hour"
private const val RAIN_DATA_THREE_HOURS = "rain_three_hours"

class WeatherDataTypeConverter {

    @TypeConverter
    fun fromListToString(weatherDescriptionDataList: List<WeatherDescriptionData>): String {
        val jsonArray = JSONArray()
        for (data in weatherDescriptionDataList) {
            val jsonObject = JSONObject()
            jsonObject.put(WEATHER_DESCRIPTION_DATA_ID, data.weatherConditionId)
            jsonObject.put(WEATHER_DESCRIPTION_DATA_MAIN, data.weatherMain)
            jsonObject.put(WEATHER_DESCRIPTION_DATA_DESCRIPTION, data.weatherDescription)
            jsonObject.put(WEATHER_DESCRIPTION_DATA_ICON, data.weatherIcon)
            jsonArray.put(jsonObject)
        }
        return jsonArray.toString()
    }

    @TypeConverter
    fun fromStringToList(jsonString: String): List<WeatherDescriptionData> {
        val dataList = ArrayList<WeatherDescriptionData>()
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until (jsonArray.length() - 1)) {
            val dataJSONObject = jsonArray[i] as? JSONObject
            if (dataJSONObject != null) {
                val data = WeatherDescriptionData(
                    dataJSONObject.getInt(WEATHER_DESCRIPTION_DATA_ID),
                    dataJSONObject.getString(WEATHER_DESCRIPTION_DATA_MAIN),
                    dataJSONObject.getString(WEATHER_DESCRIPTION_DATA_DESCRIPTION),
                    dataJSONObject.getString(WEATHER_DESCRIPTION_DATA_ICON)
                )
                dataList.add(data)
            }
        }
        return dataList
    }


    @TypeConverter
    fun fromMainDataToString(data: MainWeatherData): String {
        val jsonObject = JSONObject()
        jsonObject.put(MAIN_WEATHER_DATA_TEMP, data.temp)
        jsonObject.put(MAIN_WEATHER_DATA_PRESSURE, data.pressure)
        jsonObject.put(MAIN_WEATHER_DATA_HUMIDITY, data.humidity)
        return jsonObject.toString()
    }

    @TypeConverter
    fun fromStringToMainData(jsonString: String): MainWeatherData {
        val jsonObject = JSONObject(jsonString)
        return MainWeatherData(
            jsonObject.getDouble(MAIN_WEATHER_DATA_TEMP),
            jsonObject.getDouble(MAIN_WEATHER_DATA_PRESSURE),
            jsonObject.getInt(MAIN_WEATHER_DATA_HUMIDITY))
    }

    @TypeConverter
    fun fromWindDataToString(data: WindData): String {
        val jsonObject = JSONObject()
        jsonObject.put(WIND_DATA_SPEED, data.windSpeed)
        jsonObject.put(WIND_DATA_DIRECTION, data.windDirection)
        return jsonObject.toString()
    }

    @TypeConverter
    fun fromStringToWindData(jsonString: String): WindData {
        val jsonObject = JSONObject(jsonString)
        return WindData(
            jsonObject.getDouble(WIND_DATA_SPEED),
            jsonObject.getDouble(WIND_DATA_DIRECTION))
    }

    @TypeConverter
    fun cloudsDataToInt(data: CloudsData): Int {
        return data.all
    }

    @TypeConverter
    fun intToCloudsData(all: Int): CloudsData {
        return CloudsData(all)
    }

    @TypeConverter
    fun weatherSysDataToString(data: WeatherSysData): String {
        val jsonObject = JSONObject()
        jsonObject.put(WEATHER_SYS_DATA_COUNTRY_CODE, data.countryCode)
        jsonObject.put(WEATHER_SYS_DATA_SUNRISE_TIME, data.sunriseTime)
        jsonObject.put(WEATHER_SYS_DATA_SUNSET_TIME, data.sunsetTime)
        return jsonObject.toString()
    }

    @TypeConverter
    fun stringToWeatherSysData(jsonString: String): WeatherSysData {
        val jsonObject = JSONObject(jsonString)
        return WeatherSysData(
            jsonObject.getString(WEATHER_SYS_DATA_COUNTRY_CODE),
            jsonObject.getLong(WEATHER_SYS_DATA_SUNRISE_TIME),
            jsonObject.getLong(WEATHER_SYS_DATA_SUNSET_TIME)
        )
    }

    @TypeConverter
    fun snowDataToString(data: SnowData): String {
        val jsonObject = JSONObject()
        jsonObject.put(SNOW_DATA_ONE_HOUR, data.snow1h)
        jsonObject.put(SNOW_DATA_THREE_HOURS, data.snow3h)
        return jsonObject.toString()
    }

    @TypeConverter
    fun stringToSnowData(jsonString: String): SnowData {
        val jsonObject = JSONObject(jsonString)
        return SnowData(
            jsonObject.getInt(SNOW_DATA_ONE_HOUR),
            jsonObject.getInt(SNOW_DATA_THREE_HOURS)
        )
    }

    @TypeConverter
    fun rainDataToString(data: RainData): String {
        val jsonObject = JSONObject()
        jsonObject.put(RAIN_DATA_ONE_HOUR, data.rain1h)
        jsonObject.put(RAIN_DATA_THREE_HOURS, data.rain3h)
        return jsonObject.toString()
    }

    @TypeConverter
    fun stringToRainData(jsonString: String): RainData {
        val jsonObject = JSONObject(jsonString)
        return RainData(
            jsonObject.getInt(RAIN_DATA_ONE_HOUR),
            jsonObject.getInt(RAIN_DATA_THREE_HOURS)
        )
    }
}