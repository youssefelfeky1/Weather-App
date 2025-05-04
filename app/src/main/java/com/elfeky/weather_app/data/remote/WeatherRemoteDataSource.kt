package com.elfeky.weather_app.data.remote

import android.os.Handler
import android.os.Looper
import com.elfeky.weather_app.common.Constants.API_KEY
import com.elfeky.weather_app.common.Constants.BASE_URL
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.elfeky.weather_app.common.Result
import java.io.IOException
import java.io.InputStream


class WeatherRemoteDataSource {
    private companion object {
        const val TIMEOUT = 15000
    }

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler = Handler(Looper.getMainLooper())

    fun fetchWeather(
        latitude: Double,
        longitude: Double,
        callback: (Result<String>) -> Unit
    ) {
        val urlString = BASE_URL+ "${latitude},${longitude}?unitGroup=metric&include=current&key=$API_KEY&contentType=json"
        mainThreadHandler.post { callback(Result.Loading) }
        executorService.execute {
            val result = try {
                makeHttpRequest(urlString)
            } catch (e: Exception) {
                Result.Failure(e)
            }
            mainThreadHandler.post { callback(result) }
        }
    }

    private fun makeHttpRequest(urlString: String): Result<String> {
        var connection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        var errorStream: InputStream? = null

        try {
            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            connection.apply {
                connectTimeout = TIMEOUT
                readTimeout = TIMEOUT
                requestMethod = "GET"
                doInput = true
            }

            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode in 200..299) {
                inputStream = connection.inputStream
                inputStream?.bufferedReader()?.use { reader ->
                    return Result.Success(reader.readText())
                }
            } else {
                errorStream = connection.errorStream
                val errorMessage = errorStream?.bufferedReader()?.use { it.readText() }
                    ?: "HTTP error: $responseCode"
                return Result.Failure(IOException(errorMessage))
            }
        }catch (e: Exception) {
            return Result.Failure(e)
        }
        finally {
            inputStream?.close()
            errorStream?.close()
            connection?.disconnect()
        }

        return Result.Failure(IOException("Unknown error occurred"))
    }

    fun shutdown() {
        executorService.shutdown()
    }
}