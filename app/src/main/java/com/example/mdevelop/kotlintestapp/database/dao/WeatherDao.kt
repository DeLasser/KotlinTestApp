package com.example.mdevelop.kotlintestapp.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.mdevelop.kotlintestapp.database.model.WeatherData
import io.reactivex.Flowable

/**
 * Created by mdevelop on 24.08.2017.
 */
@Dao
public interface WeatherDao {
    @Query("SELECT * FROM weather")
    fun getAll(): List<WeatherData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: WeatherData)

    @Query("SELECT * FROM weather WHERE city = :city")
    fun getUserById(city: String): Flowable<WeatherData>
}