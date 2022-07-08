package ru.eababurin.weather.utils

import com.google.gson.Gson
import ru.eababurin.weather.BuildConfig
import ru.eababurin.weather.model.dto.WeatherDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.HttpURLConnection

object WeatherLoader {
    fun request(lat: Double, lon: Double, block: (weather: WeatherDTO) -> Unit) {
        val url = URL("https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon")

        val connection = url.openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.addRequestProperty("X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY)

        Thread {
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val weatherDTO: WeatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)

            block(weatherDTO)
        }.start()
    }
}