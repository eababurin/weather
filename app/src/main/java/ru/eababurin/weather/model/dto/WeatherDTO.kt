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

data class Info(
    @SerializedName("lat")
    val lat: Double, // 55.7522
    @SerializedName("lon")
    val lon: Double, // 37.6156
    @SerializedName("url")
    val url: String // https://yandex.ru/pogoda/213?lat=55.7522&lon=37.6156
)

data class Fact(
    @SerializedName("condition")
    val condition: String, // cloudy
    @SerializedName("daytime")
    val daytime: String, // d
    @SerializedName("feels_like")
    val feelsLike: Int, // 20
    @SerializedName("humidity")
    val humidity: Int, // 64
    @SerializedName("icon")
    val icon: String, // bkn_d
    @SerializedName("obs_time")
    val obsTime: Int, // 1657260000
    @SerializedName("polar")
    val polar: Boolean, // false
    @SerializedName("pressure_mm")
    val pressureMm: Int, // 754
    @SerializedName("pressure_pa")
    val pressurePa: Int, // 1005
    @SerializedName("season")
    val season: String, // summer
    @SerializedName("temp")
    val temp: Int, // 20
    @SerializedName("wind_dir")
    val windDir: String, // n
    @SerializedName("wind_gust")
    val windGust: Int, // 6
    @SerializedName("wind_speed")
    val windSpeed: Double // 2.1
)

data class Forecast(
    @SerializedName("date")
    val date: String, // 2022-07-08
    @SerializedName("date_ts")
    val dateTs: Int, // 1657227600
    @SerializedName("moon_code")
    val moonCode: Int, // 13
    @SerializedName("moon_text")
    val moonText: String, // moon-code-13
    @SerializedName("parts")
    val parts: List<Part>,
    @SerializedName("sunrise")
    val sunrise: String, // 03:56
    @SerializedName("sunset")
    val sunset: String, // 21:12
    @SerializedName("week")
    val week: Int // 27
)

data class Part(
    @SerializedName("condition")
    val condition: String, // cloudy
    @SerializedName("daytime")
    val daytime: String, // d
    @SerializedName("feels_like")
    val feelsLike: Int, // 25
    @SerializedName("humidity")
    val humidity: Int, // 51
    @SerializedName("icon")
    val icon: String, // bkn_d
    @SerializedName("part_name")
    val partName: String, // day
    @SerializedName("polar")
    val polar: Boolean, // false
    @SerializedName("prec_mm")
    val precMm: Int, // 0
    @SerializedName("prec_period")
    val precPeriod: Int, // 360
    @SerializedName("prec_prob")
    val precProb: Int, // 0
    @SerializedName("pressure_mm")
    val pressureMm: Int, // 753
    @SerializedName("pressure_pa")
    val pressurePa: Int, // 1003
    @SerializedName("temp_avg")
    val tempAvg: Int, // 24
    @SerializedName("temp_max")
    val tempMax: Int, // 25
    @SerializedName("temp_min")
    val tempMin: Int, // 23
    @SerializedName("wind_dir")
    val windDir: String, // nw
    @SerializedName("wind_gust")
    val windGust: Double, // 6.4
    @SerializedName("wind_speed")
    val windSpeed: Double // 2.2
)