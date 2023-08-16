package com.example.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GetWeatherAPI {

    @GET
    fun getWeatherData(@Url url : String) : Call<WeatherC>

}