package com.pawanjeswani.app10xweather.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pawanjeswani.app10xweather.R
import com.pawanjeswani.app10xweather.databinding.ActivityMainBinding
import com.pawanjeswani.app10xweather.network.Resource
import com.pawanjeswani.app10xweather.util.hide
import com.pawanjeswani.app10xweather.util.isConnected
import com.pawanjeswani.app10xweather.util.show
import com.pawanjeswani.app10xweather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            weatherViewModel.fetchForeCast(query = "Bengaluru", units = "metric", count = 4)
        }
    }

    private fun startObserving() {
        weatherViewModel.weatherResponse.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    if(isConnected())
                        handleLoader()
                }
                is Resource.Failure -> {
                    handleErrorUi()
                }
                is Resource.Success -> {
                    val weatherResponse = it.value.body()
                    showMainUi()
                    weatherResponse?.let { response ->
                        binding.tvTodayTemp.text =
                            getString(R.string.temperature_string, response.main.temp)
                    }
                }
            }
        }
        weatherViewModel.forecastResponse.observe(this) {

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
        binding.apply {
            clError.hide()
            progressBar.hide()
            clMain.show()
        }
    }

    private fun handleErrorUi() {
        binding.apply {
            clMain.hide()
            progressBar.hide()
            clError.show()
        }
    }


}
