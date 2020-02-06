package com.example.weatherapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.forecastWeather.ForecastMainWeather
import java.util.*

class ForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {
    private var forecastWeather: List<ForecastMainWeather> = ArrayList()
    private var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast_weather, parent, false)
        return ForecastViewHolder(itemView)
    }

    fun update(forecastWeather: List<ForecastMainWeather>) {
        this.forecastWeather = forecastWeather
        listener = listener
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastWeather[position])
    }

    override fun getItemCount(): Int {
        return forecastWeather.size
    }
}