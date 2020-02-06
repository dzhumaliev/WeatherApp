package com.example.weatherapp.data.entity.currentWeather

class CloudEntity {
    var all = 0

    override fun toString(): String {
        return StringBuilder("[").append(all).append("]").toString()
        //        return "Cloud [all = "+all+"]";
    }
}
