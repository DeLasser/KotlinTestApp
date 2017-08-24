package com.example.mdevelop.kotlintestapp

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_weather.view.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.example.mdevelop.kotlintestapp.api.WeatherService
import com.example.mdevelop.kotlintestapp.database.WeatherDatabase
import com.example.mdevelop.kotlintestapp.database.model.WeatherData
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.HttpException
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by mdevelop on 23.08.2017.
 */
class WeatherFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater!!.inflate(R.layout.fragment_weather,container,false)
        view.editText.setOnEditorActionListener { _, i, _ ->
            if(i== EditorInfo.IME_ACTION_SEARCH){
                searchWeather( view.editText.text.toString(), view)
                true
            }else{
                false
            }
        }
        return view
    }

    fun searchWeather(city: String,view: View){
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .build()


        val weatherService = retrofit.create(WeatherService::class.java)
        val london = weatherService.loadWeather(city,"1b242fd88adeb74469ff5928ab9230e5")
        london.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ weatherData ->
                        view.city.text = weatherData.name
                        view.temp.text = (weatherData.main!!.temp-273.15).toString()
                        WeatherDatabase.getInstance(activity.baseContext).weatherDao()
                                .insertWeather(WeatherData(weatherData.name.toString(),
                                        weatherData.sys!!.country,weatherData.weather!![0].icon,
                                        (weatherData.main!!.temp-273.15)))
                        Picasso.with(activity.baseContext).load("http://openweathermap.org/img/w/${weatherData.weather!![0].icon}"+".png").into(view.imageView)
                })
    }

}