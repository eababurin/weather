package ru.eababurin.weather.repositories

import ru.eababurin.weather.domain.Weather

class RepositoryRemoteImpl : OneResultWeatherGetable {
    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather()
    }
}