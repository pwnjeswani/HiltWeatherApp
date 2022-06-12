package `in`.pawanjeswani.app10xweather.network

import `in`.pawanjeswani.app10xweather.BuildConfig
import `in`.pawanjeswani.app10xweather.model.ForecastResponse
import `in`.pawanjeswani.app10xweather.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DataRepository(private val dataService: APIService) {


    suspend fun fetchWeather(query: String, units: String): Response<WeatherResponse> {
        val apiKey = BuildConfig.API_KEY
        return withContext(Dispatchers.IO) {
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
    ): Response<ForecastResponse> {
        val apiKey = BuildConfig.API_KEY
        return withContext(Dispatchers.IO) {
            dataService.fetchForecast(
                app_id = apiKey,
                query = query,
                units = units,
                count = count
            )
        }
    }


    companion object {
        private var dataRepository: DataRepository? = null

        fun instance(dataService: APIService): DataRepository? {
            if (dataRepository == null) {
                dataRepository = DataRepository(dataService)
            }
            return dataRepository
        }
    }
}
