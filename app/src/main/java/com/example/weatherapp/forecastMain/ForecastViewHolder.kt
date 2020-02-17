package com.example.weatherapp.forecastMain

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.forecastWeather.ForecastList
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var description: TextView = itemView.findViewById(R.id.tv_desc_forecast)
    private var forecastImage: LottieAnimationView = itemView.findViewById(R.id.forecast_image)
    private var forecastTemp: TextView = itemView.findViewById(R.id.temp_forecast)
    private var forecastTime: TextView = itemView.findViewById(R.id.day)
    private var forecastHour: TextView = itemView.findViewById(R.id.hour)


    @SuppressLint("SetTextI18n")
    fun bind(list: ForecastList) {

        description.text = list.weather[0].description
        forecastTemp.text = list.main.temp.toString() + "ºC"
        animationChanger(list)

        val outputFormat1: DateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        val inputFormat1: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        val date1: Date = inputFormat1.parse(list.dtTxt)
        val outputText1: String = outputFormat1.format(date1)
        forecastTime.text = outputText1

        val outputFormat2: DateFormat = SimpleDateFormat("HH:mm", Locale.US)
        val inputFormat2: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        val date2: Date = inputFormat2.parse(list.dtTxt)
        val outputText2: String = outputFormat2.format(date2)
        forecastHour.text = outputText2
    }

    private fun animationChanger(list: ForecastList) {

        val way: Int? = list.weather[0].id
        val dateIffer: Boolean = getTime(list) in 7..19

        if (way in 200..202) {
            if (dateIffer) {
                forecastImage.setAnimation(R.raw.stormshowersday)
            } else {
                forecastImage.setAnimation(R.raw.storm)
            }
        }
        if (way in 210..232) {
            forecastImage.setAnimation(R.raw.thunder)
        }
        if (way in 300..321) {
            forecastImage.setAnimation(R.raw.snow_sunny)
        }
        if (way in 500..531) {
            if (dateIffer) {
                forecastImage.setAnimation(R.raw.shower)
            } else {
                forecastImage.setAnimation(R.raw.rainynight)
            }
        }
        if (way in 600..622) {
            if (dateIffer) {
                forecastImage.setAnimation(R.raw.snow)
            } else {
                forecastImage.setAnimation(R.raw.snownight)
            }
        }
        if (way in 701..781) {
            if (dateIffer) {
                forecastImage.setAnimation(R.raw.foggy)
            } else {
                forecastImage.setAnimation(R.raw.mist)
            }
        }
        if (way == 800) {
            if (dateIffer) {
                forecastImage.setAnimation(R.raw.sunny)
            } else {
                forecastImage.setAnimation(R.raw.night)
            }
        }
        if (way in 801..804) {
            if (dateIffer) {
                forecastImage.setAnimation(R.raw.cloudy)
            } else {
                forecastImage.setAnimation(R.raw.cloudynight)
            }
        }
    }

    private fun getTime(list: ForecastList): Int? { ////Взял данные с ретрофита и выбрал только время, чтоб анимация менялась
        val outputFormat: DateFormat = SimpleDateFormat("HH", Locale.US)
        val inputFormat: DateFormat =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        val date: Date = inputFormat.parse(list.dtTxt)
        val outputText: String = outputFormat.format(date)
        return outputText.toInt()
    }
}