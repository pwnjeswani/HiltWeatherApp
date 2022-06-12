package `in`.pawanjeswani.app10xweather.viewmodel

import `in`.pawanjeswani.app10xweather.network.DataRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val repository: DataRepository
) : ViewModel() {


    suspend fun fetchWeather(query: String, units: String) =
        withContext(Dispatchers.IO) { repository.fetchWeather(query, units) }

    suspend fun fetchForeCast(query: String, units: String, count: Int) =
        withContext(Dispatchers.IO) { repository.fetchForecast(query, units, count) }
}