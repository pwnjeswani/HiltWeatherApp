package com.pawanjeswani.app10xweather.network

import com.pawanjeswani.app10xweather.BuildConfig
import com.pawanjeswani.app10xweather.model.ForecastResponse
import com.pawanjeswani.app10xweather.model.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class DataRepository @Inject constructor(private val dataService: APIService) : SafeApiCall {

    suspend fun fetchWeather(query: String, units: String): Resource<Response<WeatherResponse>> {
        val apiKey = BuildConfig.API_KEY
        return safeApi {
            dataService.fetchWeather(
                app_id = apiKey,
                query = query,
                units = units
            )
        }
    }

    suspend fun fetchForecast(
        query: String,
        units: String,
        count: Int
    ): Resource<Response<ForecastResponse>> {
        val apiKey = BuildConfig.API_KEY
        return safeApi {
            dataService.fetchForecast(
                app_id = apiKey,
                query = query,
                units = units,
                count = count
            )
        }
    }

}
