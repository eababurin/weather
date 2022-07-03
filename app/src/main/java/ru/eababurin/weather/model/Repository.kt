package ru.eababurin.weather.model

import ru.eababurin.weather.domain.Weather

fun interface OneResultWeatherGetable{
    fun getWeather(lat: Double, lon: Double): Weather
}

fun interface MultiResultWeatherGetable {
    fun getListWeather(location: Location): List<Weather>
}

sealed class Location {
    object Russian: Location()
    object World: Location()
}