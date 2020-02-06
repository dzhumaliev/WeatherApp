package com.example.weatherapp.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.forecastWeather.ForecastMainWeather

class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var description: TextView? = null


    init {
        description = itemView.findViewById(R.id.tv_desc_forecast)

    }

    fun bind(list: ForecastMainWeather) {
        description?.text = list.list[0].weather[0].description


//            list[adapterPosition].list[adapterPosition].weather[adapterPosition]
//            .description


    }


}