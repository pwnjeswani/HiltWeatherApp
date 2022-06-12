package com.pawanjeswani.app10xweather.view.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pawanjeswani.app10xweather.R
import com.pawanjeswani.app10xweather.databinding.ActivityMainBinding
import com.pawanjeswani.app10xweather.model.WeatherData
import com.pawanjeswani.app10xweather.network.Resource
import com.pawanjeswani.app10xweather.util.hide
import com.pawanjeswani.app10xweather.util.isConnected
import com.pawanjeswani.app10xweather.util.show
import com.pawanjeswani.app10xweather.view.adapter.ForecastAdapter
import com.pawanjeswani.app10xweather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        ForecastAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvForecast.adapter = adapter
        binding.tvCityName.text = getString(R.string.city_name)
        binding.btnRetry.setOnClickListener {
            fetchWeatherAndForecast()
        }
        startObserving()
        fetchWeatherAndForecast()
    }

    private fun fetchWeatherAndForecast() {
        lifecycleScope.launch {
            weatherViewModel.fetchWeather(query = "Bengaluru", units = "metric")
        }
        lifecycleScope.launch {
            weatherViewModel.fetchForeCast(
                lat = 12.9716,
                lon = 77.5946,
                exclude = arrayListOf("current", "minutely", "hourly", "alerts"),
                units = "metric",
                count = 4
            )
        }
    }

    private fun startObserving() {
        weatherViewModel.weatherResponse.observe(this) {
            when (it) {
                is Resource.Success -> {
                    val weatherResponse = it.value.body()
                    showMainUi()
                    weatherResponse?.let { response ->
                        binding.tvTodayTemp.text =
                            getString(R.string.temperature_string, response.main.temp?.roundToInt())
                    }
                }
                else -> handleLoaderAndError(it)
            }
        }
        weatherViewModel.forecastResponse.observe(this) {
            when (it) {
                is Resource.Success -> {
                    val weatherResponse = it.value.body()
                    showMainUi()
                    weatherResponse?.let { response ->
                        adapter.submitList(response.dailyList.subList(1,5))
                    }
                }
                else -> handleLoaderAndError(it)
            }
        }
    }

    private fun handleLoaderAndError(it: Resource<Any>) {
        when (it) {
            is Resource.Loading -> {
                if (isConnected())
                    handleLoader()
            }
            is Resource.Failure -> {
                handleErrorUi()
            }
            else -> {}
        }
    }

    private fun handleLoader() {
        binding.apply {
            clError.hide()
            progressBar.show()
            clMain.hide()
        }
    }

    private fun showMainUi() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.apply {
                clError.hide()
                progressBar.hide()
                clMain.show()
            }
        }, 500)
    }

    private fun handleErrorUi() {
        binding.apply {
            clMain.hide()
            progressBar.hide()
            clError.show()
        }
    }


}
