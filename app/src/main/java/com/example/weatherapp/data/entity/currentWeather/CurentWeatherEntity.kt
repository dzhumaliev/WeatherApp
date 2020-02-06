package com.example.weatherapp.data.entity.currentWeather

open class CurrentWeatherEntity {
    var dt = 0
    var coord: CoordEntity? = null
    var visibility = 0
    var weather: List<WeatherEntity>? = null
    var name: String? = null
    var cod = 0
    var main: MainEntity? = null
    var clouds: CloudEntity? = null
    var id = 0
    var sys: SysEntity? = null
    var base: String? = null
    var wind: WindEntity? = null

    override fun toString(): String {
        return StringBuilder("[").append(dt).append(',').append(coord)
            .append(',').append(visibility)
            .append(',').append(weather).append(',').append(name).append(',')
            .append(cod).append(',')
            .append(main).append(',').append(clouds).append(',').append(id)
            .append(',').append(sys)
            .append(',').append(base).append(',').append(wind).append("]").toString()
        //        return "Weather [dt = " + dt + ", coord = " + coord + ", visibility = " + visibility + ", weather = " + weather + ", name = " + name + ", cod = " + cod + ", main = " + main + ", clouds = " + clouds + ", id = " + id + ", sys = " + sys + ", base = " + base + ", wind = " + wind + "]";
    }

    fun getDouble(temp: String?) {}
    fun getString(description: String?): String? {
        return null
    }
}

