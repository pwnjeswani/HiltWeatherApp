package com.pawanjeswani.app10xweather.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pawanjeswani.app10xweather.databinding.ActivityMainBinding
import com.pawanjeswani.app10xweather.util.ApiException
import com.pawanjeswani.app10xweather.util.NoInternetException
import com.pawanjeswani.app10xweather.util.snackbar
import com.pawanjeswani.app10xweather.util.toast
import com.pawanjeswani.app10xweather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val authResponse =
                        weatherViewModel.fetchForeCast(query = "Bengaluru", units = "metric", count = 4)
                    if (authResponse.cod == "200") {
                        withContext(Dispatchers.Main) {
                            toast(authResponse.list[0].dtTxt)
                        }

                    } else {
                        withContext(Dispatchers.Main) {
                            binding.rootLayout.snackbar(authResponse.cod.toString())
                        }
                    }
                } catch (e: ApiException) {
                    e.printStackTrace()
                } catch (e: NoInternetException) {
                    e.printStackTrace()
                }
            }
        }

    }
}
