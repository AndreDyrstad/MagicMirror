package com.ady.magicmirror.io

import com.ady.magicmirror.models.SomeKindOfCollection
import com.ady.magicmirror.models.Weather
import com.ady.magicmirror.util.someConstant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherEndpoint {
    @Headers("User-Agent: MagicMirror")
    @GET("/weatherapi/locationforecast/2.0/compact?lat=60.39299&lon=5.32415")
    fun getWeather(): Call<Weather.WeatherData>
}