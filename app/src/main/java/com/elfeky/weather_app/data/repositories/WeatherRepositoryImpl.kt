package com.elfeky.weather_app.data.repositories

import com.elfeky.weather_app.data.remote.WeatherRemoteDataSource
import com.elfeky.weather_app.domain.models.WeatherData
import com.elfeky.weather_app.domain.repositories.WeatherRepository
import com.elfeky.weather_app.utils.Result
import com.elfeky.weather_app.utils.Result.*
import com.elfeky.weather_app.data.remote.mappers.WeatherMapper

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        callback: (Result<WeatherData>) -> Unit
    ) {
        remoteDataSource.fetchWeather(latitude, longitude) { result ->
            when (result) {
                is Result.Success -> {
                    try {
                        callback(WeatherMapper.fromJson(result.data))
                    } catch (e: Exception) {
                        callback(Failure(e))
                    }
                }
                is Result.Failure -> callback(result)
                is Result.Loading -> callback(result)
            }
        }
    }
}