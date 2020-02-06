package com.example.weatherapp.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.forecastWeather.ForecastMainWeather
import com.example.weatherapp.data.network.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_forecast.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastActivity : AppCompatActivity() {

    var adapter: ForecastAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        getForecastWeather()

        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = ForecastAdapter()
        recycler_view.adapter

    }

    private fun getForecastWeather() {
        RetrofitBuilder().create().getForecastWeather(524901, BuildConfig.WEATHER_KEY )
            .enqueue(object : Callback<List<ForecastMainWeather>> {
                override fun onResponse(
                    call: Call<List<ForecastMainWeather>>,
                    response: Response<List<ForecastMainWeather>>
                ) {

//                    Log.d("olololo", respons)
                    if (response.body() != null) {
                        getForecastData(response.body()!!)
                        Log.e("TAG", "onResponse: $response")

                        Log.e("ololo", "rrrrrrrrrrr")

                    }
                }


                override fun onFailure(
                    call: Call<List<ForecastMainWeather>>,
                    t: Throwable      ///no internet toast

                ) {

                    Log.e("ololo", t.message)

                }
            })
    }

    private fun getForecastData(entity: List<ForecastMainWeather>) {
        adapter?.update(entity)

    }
}
