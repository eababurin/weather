package ru.eababurin.weather.model

import ru.eababurin.weather.domain.Weather

interface Repository {
    fun getWeather(lat: Double, lon: Double): Weather
    fun getListWeather(): List<Weather>
}

class RepositoryLocalImpl : Repository {
    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather()
    }

    override fun getListWeather(): List<Weather> {
        return listOf(Weather())
    }
}

class RepositoryRemoteImpl : Repository {
    override fun getListWeather(): List<Weather> {
        Thread {
            Thread.sleep(200L)

        }.start()
        return listOf(Weather())
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        Thread {
            Thread.sleep(300L)

        }.start()
        return Weather()
    }

}