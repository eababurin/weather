package ru.eababurin.weather.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.eababurin.weather.utils.getDefaultCity

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    var temperature: Int = 0,
    var feelsLike: Int = 0
) : Parcelable

