package com.example.weatherapp.data.entity.currentWeather

class WindEntity {
    var deg = 0.0
    var speed = 0.0

    override fun toString(): String {
        return StringBuilder("[").append(deg).append(',').append(speed)
            .append("]").toString()
        //        return "Wind [deg = "+deg+", speed = "+speed+"]";
    }
}