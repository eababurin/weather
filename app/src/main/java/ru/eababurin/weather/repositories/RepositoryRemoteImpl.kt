package ru.eababurin.weather.repositories

import com.google.gson.Gson
import ru.eababurin.weather.BuildConfig
import ru.eababurin.weather.model.dto.WeatherDTO
import ru.eababurin.weather.utils.getLines
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RepositoryRemoteImpl : OneResultWeatherGetable {
    override fun getWeather(lat: Double, lon: Double, block: (weather: WeatherDTO) -> Unit) {
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