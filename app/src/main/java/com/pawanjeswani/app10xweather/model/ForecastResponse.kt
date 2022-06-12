package com.pawanjeswani.app10xweather.model


import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("message")
    val message: Any?,
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("daily")
    val dailyList: ArrayList<WeatherData>
)


data class WeatherData(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("temp")
    val temp: Temp,
    @SerializedName("main")
    val main: Main
)

data class Temp(
    @SerializedName("day")
    val day: Double
)
data class City(
    @SerializedName("name")
    val name: String
)
