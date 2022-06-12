package com.pawanjeswani.app10xweather.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.pawanjeswani.app10xweather.R
import com.pawanjeswani.app10xweather.databinding.ForecastItemBinding
import com.pawanjeswani.app10xweather.model.WeatherData
import com.pawanjeswani.app10xweather.util.getDayOfWeek
import java.util.*
import kotlin.math.roundToInt

class ForecastViewHolder(private val binding: ForecastItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindTo(field: WeatherData) {
        field.apply {
            binding.let {
                val dayOfWeek =
                    it.tvWeekday.getDayOfWeek(dt * 1000, cal = Calendar.getInstance())

                it.tvWeekday.text = dayOfWeek
                it.tvAvgTemp.text = binding.root.context.getString(
                    R.string.celsius_string,
                    temp.day.roundToInt()
                )
            }
        }
    }
}