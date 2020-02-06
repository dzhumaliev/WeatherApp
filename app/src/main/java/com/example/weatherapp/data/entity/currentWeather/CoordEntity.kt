package com.example.weatherapp.data.entity.currentWeather

class CoordEntity {
    var lon = 0.0
    var lat = 0.0

    override fun toString(): String {
        return StringBuilder("[").append(lat).append(',').append(lon)
            .append("]").toString()
        //        return "Coord [lon = "+lon+", lat = "+lat+"]";
    }
}