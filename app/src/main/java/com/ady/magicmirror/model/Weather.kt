package com.ady.magicmirror.model

class Weather {

    data class WeatherData(
        val properties: Properties
    )

    data class Properties(
        val timeseries: List<Timeseries>
    )

    data class Timeseries(
        val time: String,
        val data: Data,
    )

    data class Data(
        val instant: Instant,
        val next_12_hours: Summary,
        val next_1_hours: Summary,
        val next_6_hours: Summary
    )

    data class Instant(
        val details: Details
    )

    data class Details(
        val air_pressure_at_sea_level: Double,
        val air_temperature: Double,
        val cloud_area_fraction: Double,
        val relative_humidity: Double,
        val wind_from_direction: Double,
        val wind_speed: Double
    )

    data class Summary(
        val symbol_code: String
    )
}