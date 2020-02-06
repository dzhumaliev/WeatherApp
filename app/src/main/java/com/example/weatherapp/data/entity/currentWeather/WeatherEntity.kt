package com.example.weatherapp.data.entity.currentWeather

class WeatherEntity(
    var description: String,
    var icon: String,
    var main: String,
    var id: Int
) {

    override fun toString(): String {
        return StringBuilder("[").append(icon).append(',').append(id)
            .append(',').append(description).append(',').append(main).append("]")
            .toString()
    }

}
