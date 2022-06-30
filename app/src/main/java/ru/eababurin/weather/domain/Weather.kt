package ru.eababurin.weather.domain

data class Weather(val city: City = getDefaultCity(), val temperature: Int = 30, val feelsLike: Int = 35)