package com.pawanjeswani.app10xweather.network


import com.pawanjeswani.app10xweather.model.ForecastResponse
import com.pawanjeswani.app10xweather.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("data/2.5/weather")
    suspend fun fetchWeather(
        @Query("APPID") app_id: String,
        @Query("q") query: String,
        @Query("units") units: String
    ): Response<WeatherResponse>

    @GET("data/2.5/onecall")
    suspend fun fetchForecast(
        @Query("APPID") app_id: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: List<String>,
        @Query("units") units: String,
        @Query("cnt") count: Int
    ): Response<ForecastResponse>

}