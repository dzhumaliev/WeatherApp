package com.example.weatherapp.data.entity.forecastWeather

data class ForecastWeatherEntity(

    var dt: Int,
    var pressure: Double,
    var humidity: Int,
    var speed: Double,
    var deg: Int,
    var clouds: Int,
    var snow: Double,
    var weather: List<Weather>,
    var temp: List<Temp>


)
