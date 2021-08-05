package com.ady.magicmirror.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ady.magicmirror.io.SoMeTypeOfEndPoints
import com.ady.magicmirror.io.WeatherEndpoint
import com.ady.magicmirror.models.Weather
import com.ady.magicmirror.util.LogUtils.debug
import com.ady.magicmirror.util.enqueue
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel() {
    val weatherText: MutableLiveData<String> = MutableLiveData("Test")

    init {
        getSoMeData()
    }

    private fun getSoMeData() {
        val soMeLogger = HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        val soMeOkHttp = OkHttpClient()
            .newBuilder()
            .apply {
                addInterceptor(soMeLogger)
            }.build()

        return Retrofit
            .Builder()
            .apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl("https://api.met.no")
                client(soMeOkHttp)
            }
            .build()
            .create(WeatherEndpoint::class.java)
            .getWeather()
            .enqueue(object: Callback<Weather.WeatherData>{
                override fun onResponse(
                    call: Call<Weather.WeatherData>,
                    response: Response<Weather.WeatherData>
                ) {
                    if(response.code() == 200) {
                        val weatherResponse = response.body()!!
                        val someData = weatherResponse.properties.timeseries[0].data.instant.details.air_temperature.toString()
                        debug(this,someData)
                        weatherText.value = someData
                        println(weatherText.value)
                    }
                }

                override fun onFailure(call: Call<Weather.WeatherData>, t: Throwable) {
                    System.err.println("Error getting weather data")
                }
            }) //Normally (object : Callback<SomeModel> { ... })

    }
}