package com.example.weatherapp.data.entity.currentWeather

class SysEntity {
    var country: String? = null
    var sunrise = 0
    var sunset = 0
    var id = 0
    var type = 0
    var message = 0.0

    override fun toString(): String {
        return StringBuilder("[").append(country).append(',').append(sunrise)
            .append(',').append(sunset)
            .append(',').append(id).append(type).append(message).append("]")
            .toString()
        //        return "Sys [country = "+country+", sunrise = "+sunrise+", sunset = "+sunset+", id = "+id+", type = "+type+", message = "+message+"]";
    }
}

