package com.example.rxjava

data class WeatherResponse(val temperature: String, val wind: String, val description: String)

fun mapToDisplayItem(response: WeatherResponse): WeatherResponse {
    val temperature = "${response.temperature}"
    val wind = "${response.wind}"
    val description = "${response.description}"
    return WeatherResponse(temperature, wind, description)

}