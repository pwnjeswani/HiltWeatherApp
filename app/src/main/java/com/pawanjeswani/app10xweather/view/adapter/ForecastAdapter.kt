package com.pawanjeswani.app10xweather.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pawanjeswani.app10xweather.R
import com.pawanjeswani.app10xweather.databinding.ForecastItemBinding
import com.pawanjeswani.app10xweather.model.WeatherData
import com.pawanjeswani.app10xweather.util.getDayOfWeek
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


class ForecastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var forecastList = mutableListOf<WeatherData>()

    fun submitList(list: MutableList<WeatherData>) {
        forecastList = list
        notifyItemRangeChanged(0, forecastList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ForecastViewHolder(
            ForecastItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return forecastList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ForecastViewHolder).bindTo(field = forecastList[position])
    }
}