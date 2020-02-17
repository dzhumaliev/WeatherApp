package com.example.weatherapp.main

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.BuildConfig.WEATHER_KEY
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.currentWeather.CurrentWeatherEntity
import com.example.weatherapp.data.network.RetrofitBuilder
import com.example.weatherapp.forecastMain.ForecastActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.String
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*


open class MainActivity : AppCompatActivity() {

    private lateinit var animFadeIn: Animation
    lateinit var animBtn: Animation

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        backgroundChanger()
        retrofitCurrentMaker()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun backgroundChanger() {
        if (getHour() in 7..19) {
            main.setBackgroundResource(R.drawable.backgroount_night)
//            window.navigationBarColor = getColor(R.color.day)
//            window.statusBarColor = getColor(R.color.day)
        } else {
            textDesc.setTextColor(Color.parseColor("#000000"))
            textCelsius.setTextColor(Color.parseColor("#000000"))
            city.setTextColor(Color.parseColor("#000000"))
        }
    }

    private fun retrofitCurrentMaker() {
        RetrofitBuilder().create()
            .getCurrentWeather(getString(R.string.city_name), WEATHER_KEY, "metric")
            ?.enqueue(object : Callback<CurrentWeatherEntity?> {
                override fun onResponse(
                    call: Call<CurrentWeatherEntity?>,
                    response: Response<CurrentWeatherEntity?>
                ) {
                    Log.d("ololo", "url main " + call.request().url)
                    val data: CurrentWeatherEntity? = response.body()
                    data?.weather
                    getData(data)
                }
                override fun onFailure(call: Call<CurrentWeatherEntity?>, t: Throwable) {

                }
            })
    }

    private fun animationChanger(data: CurrentWeatherEntity?) {
        val way: Int? = data!!.weather?.get(0)?.id
        val dateIffer: Boolean = getHour() in 7..19
        val animFadeInUnit: Unit? = imageView.startAnimation(animFadeIn)

        if (way in 200..202) {
            if (dateIffer) {
                imageView.setAnimation(R.raw.stormshowersday)
                animFadeInUnit

            } else {
                imageView.setAnimation(R.raw.storm)
                animFadeInUnit
            }
        }
        if (way in 210..232) {
            imageView.setAnimation(R.raw.thunder)
            animFadeInUnit
        }
        if (way in 300..321) {
            imageView.setAnimation(R.raw.snow_sunny)
            animFadeInUnit
        }
        if (way in 500..531) {
            if (dateIffer) {
                imageView.setAnimation(R.raw.shower)
                animFadeInUnit
            } else {
                imageView.setAnimation(R.raw.rainynight)
                animFadeInUnit
            }
        }
        if (way in 600..622) {
            if (dateIffer) {
                imageView.setAnimation(R.raw.snow)
                animFadeInUnit

            } else {
                imageView.setAnimation(R.raw.snownight)
                animFadeInUnit
            }
        }
        if (way in 701..781) {
            if (dateIffer) {
                imageView.setAnimation(R.raw.foggy)
                animFadeInUnit
            } else {
                imageView.setAnimation(R.raw.mist)
                animFadeInUnit
            }
        }
        if (way == 800) {
            if (dateIffer) {
                imageView.setAnimation(R.raw.sunny)
                animFadeInUnit
            } else {
                imageView.setAnimation(R.raw.night)
                animFadeInUnit
            }
        }
        if (way in 801..804) {
            if (dateIffer) {
                imageView.setAnimation(R.raw.cloudy)
                animFadeInUnit
            } else {
                imageView.setAnimation(R.raw.cloudynight)
                animFadeInUnit
            }
        }
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun getData(data: CurrentWeatherEntity?) {
        animFadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.anim)
        textCelsius.text = StringBuilder(String.valueOf(data!!.main?.temp) + "ºC")
        textCelsius.startAnimation(animFadeIn)
        textDesc.text = StringBuilder(String.valueOf(data.weather?.get(0)?.description))
        textDesc.startAnimation(animFadeIn)
        city.text = StringBuilder(String.valueOf(data.name))
        city.startAnimation(animFadeIn)
        tv_wind.text = StringBuilder(String.valueOf(data.wind?.speed) + " m/s")
        tv_humidity.text = StringBuilder(String.valueOf(data.main?.humidity) + " %")
        tv_pressure.text = StringBuilder(String.valueOf(data.main?.pressure) + " hpa")
        textDesc.text = textDesc.text.substring(0, 1).toUpperCase() + textDesc.text.substring(1)
        animationChanger(data)
        getLocalDate()
    }

    private fun getLocalDate() {
        val calendar = Calendar.getInstance()
        @SuppressLint("SimpleDateFormat")
        val sdf1 = SimpleDateFormat("dd/MM/YYYY")
        val formattedDay = sdf1.format(calendar.time)
        date.startAnimation(animFadeIn)
        date.text = formattedDay

        val curTime = Calendar.getInstance()
        @SuppressLint("SimpleDateFormat")
        val sdf2 = SimpleDateFormat("HH:mm")
        val formattedHour = sdf2.format(curTime.time)
        time.startAnimation(animFadeIn)
        time.text = formattedHour

        btn_forecast.setOnClickListener {
            val i = Intent(this, ForecastActivity::class.java)
            val sharedView: View = btn_forecast
            val transitionName = getString(R.string.btn_forecast_text)


            val transitionActivityOptions =
                ActivityOptions.makeSceneTransitionAnimation(
                    this@MainActivity,
                    sharedView,
                    transitionName
                )
            startActivity(i, transitionActivityOptions.toBundle())
        }

        animBtn = AnimationUtils.loadAnimation(this, R.anim.translate)
        first_card_view.visibility = View.VISIBLE
        first_card_view.startAnimation(animBtn)
        second_card_view.visibility = View.VISIBLE
        second_card_view.startAnimation(animBtn)
        third_card_view.visibility = View.VISIBLE
        third_card_view.startAnimation(animBtn)

    }


    private fun getHour(): Int {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    }


}