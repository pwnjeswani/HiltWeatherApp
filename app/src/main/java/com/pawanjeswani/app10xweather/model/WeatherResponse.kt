package com.pawanjeswani.app10xweather.model


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("message")
    val message: Any?,
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String
)

data class Main(
    @SerializedName("temp")
    val temp: Double?
)
