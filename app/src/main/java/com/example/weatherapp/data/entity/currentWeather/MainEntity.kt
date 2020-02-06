package com.example.weatherapp.data.entity.currentWeather

class MainEntity : CurrentWeatherEntity() {
    var temp = 0.0
    var temp_min = 0.0
    var humidity = 0
    var pressure = 0
    var temp_max = 0.0

    override fun toString(): String {
        return StringBuilder("[").append(temp).append(',').append(temp_min)
            .append(',').append(temp_max)
            .append(',').append(humidity).append(pressure).append("]").toString()
        //        return "Main [temp = "+temp+", temp_min = "+temp_min+", humidity = "+humidity+", pressure = "+pressure+", temp_max = "+temp_max+"]";
    }
}
