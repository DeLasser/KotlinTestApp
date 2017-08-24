package com.example.mdevelop.kotlintestapp.api

import com.example.mdevelop.kotlintestapp.api.model.WeatherModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by mdevelop on 23.08.2017.
 */
interface WeatherService {
    @GET("weather?")
    fun loadWeather(@Query("q") city: String, @Query("appid") appid: String): Observable<WeatherModel>
}
