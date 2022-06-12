package com.pawanjeswani.app10xweather.model


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("message")
    val message: Any?,
    @SerializedName("base")
    val base: String,
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String

)

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_kf")
    val tempKf: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)
