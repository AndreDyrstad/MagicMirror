package com.ady.magicmirror.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ady.magicmirror.R
import com.ady.magicmirror.model.Weather
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlin.collections.HashMap


class WeatherFragment : Fragment(R.layout.activity_weather) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestWithSomeHttpHeaders(view.findViewById(R.id.weatherText))
    }

    private fun requestWithSomeHttpHeaders(findViewById: TextView) {
        val gson = Gson()
        val queue = Volley.newRequestQueue(this.context)
        val url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=60.39299&lon=5.32415"
        val getRequest: StringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response -> // response
                val data: Weather.WeatherData = gson.fromJson(response, Weather.WeatherData::class.java)
                findViewById.text = data.properties.timeseries[0].data.instant.details.air_temperature.toString()
            },
            Response.ErrorListener { error -> // TODO Auto-generated method stub
                findViewById.text = error.toString()
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["User-Agent"] = "MagicMirror"
                return params
            }
        }
        queue.add(getRequest)
    }
}
