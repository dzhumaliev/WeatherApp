package com.example.weatherapp.forecastMain

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.BuildConfig.WEATHER_KEY
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.forecastWeather.ForecastWeather
import com.example.weatherapp.data.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import shivam.developer.featuredrecyclerview.FeatureLinearLayoutManager
import shivam.developer.featuredrecyclerview.FeaturedRecyclerView


class ForecastActivity : AppCompatActivity() {

    private var adapter: ForecastAdapter? = null

    @SuppressLint("WrongConstant", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        val recyclerView = findViewById<RecyclerView>(R.id.featured_recycler_view) as FeaturedRecyclerView
        adapter = ForecastAdapter()
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
//        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        getForecastWeather()


        val layoutManager = FeatureLinearLayoutManager(this, FeatureLinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }



    private fun getForecastWeather() {
        RetrofitBuilder().create().getForecastWeather("Bishkek", WEATHER_KEY, "metric")
            .enqueue(object : Callback<ForecastWeather> {
            override fun onResponse(
                call: Call<ForecastWeather>,
                response: Response<ForecastWeather>
            ) {
                if (response.body() != null) {
                    getForecastData(response.body())
                    Log.d("ololo", "url forecast " + call.request().url)
                }
            }

            override fun onFailure(
                call: Call<ForecastWeather>,
                t: Throwable      ///no internet toast

            ) {
            }
        })
    }

    private fun getForecastData(wea: ForecastWeather?) {
        adapter?.update(wea!!.list)


    }


}
