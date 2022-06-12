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
    @SerializedName("list")
    val list: List<WeatherData>
)


data class WeatherData(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("main")
    val main: Main
)

data class City(
    @SerializedName("name")
    val name: String
)
