package com.elfeky.weather_app.data.remote.mappers

import com.elfeky.weather_app.domain.models.WeatherData
import org.json.JSONObject
import com.elfeky.weather_app.common.Result
import com.elfeky.weather_app.domain.models.CurrentConditions
import com.elfeky.weather_app.domain.models.DayForecast
import com.elfeky.weather_app.domain.models.WeatherStation

object WeatherMapper {
    fun fromJson(json: String): Result<WeatherData> = try {
        val jsonObject = JSONObject(json)

        // Parse days array
        val daysArray = jsonObject.getJSONArray("days")
        val days = mutableListOf<DayForecast>()

        for (i in 0 until daysArray.length()) {
            val dayObject = daysArray.getJSONObject(i)
            val precipTypeArray = dayObject.optJSONArray("preciptype")

            days.add(DayForecast(
                datetime = dayObject.getString("datetime"),
                datetimeEpoch = dayObject.getLong("datetimeEpoch"),
                temp = dayObject.getDouble("temp"),
                feelslike = dayObject.getDouble("feelslike"),
                humidity = dayObject.getDouble("humidity"),
                precip = dayObject.getDouble("precip"),
                precipprob = dayObject.getDouble("precipprob"),
                windspeed = dayObject.getDouble("windspeed"),
                winddir = dayObject.getDouble("winddir"),
                pressure = dayObject.getDouble("pressure"),
                cloudcover = dayObject.getDouble("cloudcover"),
                conditions = dayObject.getString("conditions"),
                icon = dayObject.getString("icon"),
                sunrise = dayObject.getString("sunrise"),
                sunset = dayObject.getString("sunset"),
                preciptype = if (precipTypeArray != null) {
                    (0 until precipTypeArray.length()).map { precipTypeArray.getString(it) }
                } else null,
                description = dayObject.getString("description")
            ))
        }

        // Parse current conditions
        val currentConditionsObject = jsonObject.optJSONObject("currentConditions")
        val currentConditions = currentConditionsObject?.let {
            CurrentConditions(
                datetime = it.getString("datetime"),
                datetimeEpoch = it.getLong("datetimeEpoch"),
                temp = it.getDouble("temp"),
                feelslike = it.getDouble("feelslike"),
                humidity = it.getDouble("humidity"),
                precip = it.optDouble("precip").takeIf { !it.isNaN() },
                windspeed = it.getDouble("windspeed"),
                winddir = it.getDouble("winddir"),
                pressure = it.getDouble("pressure"),
                cloudcover = it.getDouble("cloudcover"),
                conditions = it.getString("conditions"),
                icon = it.getString("icon"),
                sunrise = it.getString("sunrise"),
                sunset = it.getString("sunset")
            )
        }

        // Parse stations
        val stationsObject = jsonObject.optJSONObject("stations")
        val stations = mutableMapOf<String, WeatherStation>()
        stationsObject?.keys()?.forEach { key ->
            val stationObject = stationsObject.getJSONObject(key)
            stations[key] = WeatherStation(
                id = key,
                name = stationObject.getString("name"),
                latitude = stationObject.getDouble("latitude"),
                longitude = stationObject.getDouble("longitude"),
                distance = stationObject.getDouble("distance"),
                quality = stationObject.getInt("quality")
            )
        }

        Result.Success(WeatherData(
            queryCost = jsonObject.getInt("queryCost"),
            latitude = jsonObject.getDouble("latitude"),
            longitude = jsonObject.getDouble("longitude"),
            resolvedAddress = jsonObject.getString("resolvedAddress"),
            timezone = jsonObject.getString("timezone"),
            tzOffset = jsonObject.getDouble("tzoffset"),
            days = days,
            currentConditions = currentConditions,
            stations = stations
        ))
    } catch (e: Exception) {
        Result.Failure(e)
    }
}