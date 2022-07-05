package ru.eababurin.weather.viewmodel

import ru.eababurin.weather.domain.Weather

sealed class AppState {
    data class SuccessOne(val weatherData: Weather) : AppState()
    data class SuccessMulti(val weatherList: List<Weather>) : AppState()
    data class Error(val message: String) : AppState()
    object Loading : AppState()
}