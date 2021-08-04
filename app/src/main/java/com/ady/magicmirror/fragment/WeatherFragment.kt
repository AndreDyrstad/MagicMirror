package com.ady.magicmirror.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ady.magicmirror.R
import com.ady.magicmirror.configurations.Weather.DISPLAY_HOURS
import com.ady.magicmirror.configurations.Weather.LATITUDE
import com.ady.magicmirror.configurations.Weather.LONGITUDE
import com.ady.magicmirror.model.Weather
import com.ady.magicmirror.util.APIService
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.lang.StringBuilder
import java.time.Instant
import java.time.ZoneOffset
import kotlin.collections.HashMap


class WeatherFragment : Fragment(R.layout.activity_weather) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestWithSomeHttpHeaders(view.findViewById(R.id.weatherText))
    }

    private fun requestWithSomeHttpHeaders(findViewById: TextView) {

        val gson = Gson()
        val queue = Volley.newRequestQueue(this.context)
        val url =
            "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=$LATITUDE&lon=$LONGITUDE"
        val getRequest: StringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                val data: Weather.WeatherData =
                    gson.fromJson(response, Weather.WeatherData::class.java)
                val timeSeries = data.properties.timeseries
                val t2 = timeSeries.filter {
                    DISPLAY_HOURS.contains(Instant.parse(it.time).atZone(ZoneOffset.UTC).hour)
                }

                val sb = StringBuilder()

                t2.forEach {
                    sb.append(
                        "${it.data.instant.details.air_temperature} " +
                                "${it.data.next_1_hours?.summary?.symbol_code ?: "NaN"}\n"
                    )
                }

                findViewById.text = sb.toString()
            },
            Response.ErrorListener { error ->
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
