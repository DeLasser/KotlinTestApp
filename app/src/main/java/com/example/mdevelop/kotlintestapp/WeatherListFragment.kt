package com.example.mdevelop.kotlintestapp

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mdevelop.kotlintestapp.database.WeatherDatabase
import com.example.mdevelop.kotlintestapp.database.model.WeatherData
import kotlinx.android.synthetic.main.fragment_weather_list.view.*
import java.util.*

/**
 * Created by mdevelop on 24.08.2017.
 */
class WeatherListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater!!.inflate(R.layout.fragment_weather_list, container, false)
        var list: ArrayList<String> = ArrayList()
        var weatherData: List<WeatherData> = WeatherDatabase.getInstance(activity.baseContext).weatherDao().getAll()
        for (item: WeatherData in weatherData) {
            list.add(item.country + "," + item.city + " " + item.temp + "C")
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter(activity.baseContext, android.R.layout.simple_list_item_1, list)
        view.listView.adapter = adapter
        return view
    }
}