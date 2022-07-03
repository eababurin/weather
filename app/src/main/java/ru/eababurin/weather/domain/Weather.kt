package ru.eababurin.weather.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.eababurin.weather.domain.City
import ru.eababurin.weather.domain.getDefaultCity

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 35,
    val feelsLike: Int = 43
) : Parcelable

