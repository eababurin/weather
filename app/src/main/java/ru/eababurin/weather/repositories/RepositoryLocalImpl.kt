package ru.eababurin.weather.repositories

import ru.eababurin.weather.domain.Weather
import ru.eababurin.weather.model.dto.WeatherDTO
import ru.eababurin.weather.utils.getRussianCities
import ru.eababurin.weather.utils.getWorldCities

class RepositoryLocalImpl : OneResultWeatherGetable, MultiResultWeatherGetable {
    override fun getListWeather(location: Location): List<Weather> {
        return when (location) {
            Location.Russian -> { getRussianCities() }
            Location.World -> { getWorldCities() }
        }
    }

    override fun getWeather(lat: Double, lon: Double, block: (weather: WeatherDTO) -> Unit) { }
}