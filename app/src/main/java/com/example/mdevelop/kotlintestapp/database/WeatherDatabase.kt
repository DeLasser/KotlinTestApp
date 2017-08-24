package com.example.mdevelop.kotlintestapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.mdevelop.kotlintestapp.database.dao.WeatherDao
import com.example.mdevelop.kotlintestapp.database.model.WeatherData

/**
 * Created by mdevelop on 24.08.2017.
 */
@Database(entities = arrayOf(WeatherData::class), version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        @Volatile private var INSTANCE: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        WeatherDatabase::class.java, "Weather.db")
                        .build()
    }
}