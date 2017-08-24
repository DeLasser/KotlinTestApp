package com.example.mdevelop.kotlintestapp.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by mdevelop on 24.08.2017.
 */
@Entity(tableName = "weather")
data class WeatherData(@ColumnInfo(name = "city") val city: String,
                       @ColumnInfo(name = "country") val country: String,
                       @ColumnInfo(name = "icon") val icon: String,
                       @ColumnInfo(name = "temp") val temp: Double){
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String = UUID.randomUUID().toString()
}