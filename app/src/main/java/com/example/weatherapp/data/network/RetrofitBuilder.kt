package com.example.weatherapp.data.network

import com.example.weatherapp.BuildConfig.BASE_URL_WEATHER
import com.example.weatherapp.data.entity.currentWeather.CurrentWeatherEntity
import com.example.weatherapp.data.entity.forecastWeather.ForecastMainWeather
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class RetrofitBuilder {

    fun create(): RetrofitService {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_WEATHER)
            .client(okHttpClient)
            .build()

        return retrofit.create(RetrofitService::class.java)
    }

    interface RetrofitService {

        @GET("weather")
        fun getCurrentWeather(
            @Query("q") city: String?,
            @Query("appid") appId: String?,
            @Query("units") metric: String?
        ): Call<CurrentWeatherEntity?>?


        @GET("forecast/daily")
        fun getForecastWeather(
            @Query("id") id: Int?,
            @Query("appid") appId: String?
        ): Call<List<ForecastMainWeather>>

    }



}