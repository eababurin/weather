package ru.eababurin.weather.view.details

import ru.eababurin.weather.domain.Weather

fun interface OnItemClick {
    fun onItemClick(weather: Weather)
}