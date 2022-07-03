package ru.eababurin.weather.model

import ru.eababurin.weather.domain.Weather
import ru.eababurin.weather.domain.getRussianCities
import ru.eababurin.weather.domain.getWorldCities

class RepositoryLocalImpl : MultiResultWeatherGetable, OneResultWeatherGetable {
    override fun getListWeather(location: Location): List<Weather> {
        return when (location) {
            Location.Russian -> { getRussianCities() }
            Location.World -> { getWorldCities() }
        }
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather()
    }
}

class RepositoryRemoteImpl : OneResultWeatherGetable {
    override fun getWeather(lat: Double, lon: Double): Weather {
        Thread { Thread.sleep(300L) }
            .start()
        return Weather()
    }
}