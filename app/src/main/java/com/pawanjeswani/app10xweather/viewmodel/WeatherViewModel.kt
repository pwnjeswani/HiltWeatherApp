package com.pawanjeswani.app10xweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pawanjeswani.app10xweather.model.ForecastResponse
import com.pawanjeswani.app10xweather.model.WeatherResponse
import com.pawanjeswani.app10xweather.network.DataRepository
import com.pawanjeswani.app10xweather.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _weatherResponse: MutableLiveData<Resource<Response<WeatherResponse>>> =
        MutableLiveData()
    private val _forecastResponse: MutableLiveData<Resource<Response<ForecastResponse>>> =
        MutableLiveData()
    val weatherResponse: LiveData<Resource<Response<WeatherResponse>>>
        get() = _weatherResponse
    val forecastResponse: LiveData<Resource<Response<ForecastResponse>>>
        get() = _forecastResponse

    suspend fun fetchWeather(query: String, units: String) = viewModelScope.launch {
        _weatherResponse.value = Resource.Loading
        _weatherResponse.value = repository.fetchWeather(query, units)
    }


    suspend fun fetchForeCast(
        lat: Double,
        lon: Double,
        exclude: List<String>,
        units: String,
        count: Int
    ) = viewModelScope.launch {
        _forecastResponse.value = Resource.Loading
        _forecastResponse.value = repository.fetchForecast(lat, lon, exclude, units, count)
    }
}