package ru.eababurin.weather.repositories

import ru.eababurin.weather.domain.Weather
import ru.eababurin.weather.model.dto.WeatherDTO

fun interface OneResultWeatherGetable {
    fun getWeather(lat: Double, lon: Double, block: (weather: WeatherDTO) -> Unit)
}

fun interface MultiResultWeatherGetable {
    fun getListWeather(location: Location): List<Weather>
}

sealed class Location {
    object Russian : Location()
    object World : Location()
}