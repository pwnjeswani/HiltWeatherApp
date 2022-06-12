package com.pawanjeswani.app10xweather.viewmodel

import androidx.lifecycle.ViewModel
import com.pawanjeswani.app10xweather.network.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    suspend fun fetchWeather(query: String, units: String) =
        withContext(Dispatchers.IO) {
            repository.fetchWeather(query, units)
        }


    suspend fun fetchForeCast(query: String, units: String, count: Int) =
        withContext(Dispatchers.IO) {
            repository.fetchForecast(query, units, count)
        }
}