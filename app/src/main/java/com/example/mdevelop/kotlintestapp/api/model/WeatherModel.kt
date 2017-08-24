package com.example.mdevelop.kotlintestapp.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by mdevelop on 23.08.2017.
 */
class WeatherModel (
        @SerializedName("coord") @Expose var coord: Coord?,
        @SerializedName("weather") @Expose var weather: List<Weather>?,
        @SerializedName("base") @Expose var base: String?,
        @SerializedName("main") @Expose var main: Main?,
        @SerializedName("visibility") @Expose var visibility: Int?,
        @SerializedName("wind") @Expose var wind: Wind?,
        @SerializedName("clouds") @Expose var clouds: Clouds?,
        @SerializedName("dt") @Expose var dt: Int?,
        @SerializedName("sys") @Expose var sys: Sys?,
        @SerializedName("id") @Expose var id: Int?,
        @SerializedName("name") @Expose var name: String?,
        @SerializedName("cod") @Expose var cod: Int?)