package com.example.weatherapp.data.entity.forecastWeather

data class ForecastMainWeather(
    var cod: Int,
    var message: Int,
    var city: List<City>,
    var list: List<ForecastWeatherEntity>,
    var cnt: Int
)