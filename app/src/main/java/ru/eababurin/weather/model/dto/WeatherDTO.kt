package ru.eababurin.weather.model.dto


import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("fact")
    val fact: Fact,
    @SerializedName("forecast")
    val forecast: Forecast,
    @SerializedName("info")
    val info: Info,
    @SerializedName("now")
    val now: Int, // 1657260395
    @SerializedName("now_dt")
    val nowDt: String // 2022-07-08T06:06:35.161665Z
)