package com.example.rxjava

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("/weather/{city}")
    fun getWeatherByCity(@Path("city") cityName: String): Single<WeatherResponse>

}