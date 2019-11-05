package com.weather.testapp.data.local.sql.weather

import android.content.ContentValues
import com.weather.testapp.data.local.sql.DataHelper
import com.weather.testapp.data.model.*
import javax.inject.Inject

class WeatherSQL
@Inject
constructor(val dataHelper: DataHelper) : WeatherSQLPresenter {


    companion object {
        val TABLE_WEATHER = "tbl_data_weather"
        val TABLE_CITY = "tbl_data_city"

        val COD = "cod"
        val MESSAGE = "message"
        val CNT = "cnt"
        val NAME = "name"
        val COORD_LAT = "coord_lat"
        val COORD_LON= "coord_lon"
        val COUNTRY = "country"
        val POPULATION = "population"
        val TIMEZONE = "timezone"
        val SUNRISE ="sunrise"
        val SUNSET = "sunset"

        val ID = "id"
        val DT = "dt"
        val TEMP = "temp"
        val TEMP_MIN = "temp_min"
        val TEMP_MAX = "temp_max"
        val PRESSURE = "pressure"
        val SEA_LEVEL = "sea_level"
        val GRND_LEVEL = "grnd_level"
        val HUMIDITY = "humidity"
        val TEMP_KF = "temp_kf"
        val WEATHER_ID = "weather_id"
        val WEATHER_MAIN = "weather_main"
        val WEATHER_DESCRIPTION = "weather_description"
        val WEATHER_ICON = "weather_icon"
        val CLOUDS_ALL = "clouds_all"
        val WIND_SPEED = "wind_speed"
        val WIND_DEG = "wind_deg"
        val RAIN_3H = "rain_3h"
        val SYS_POD = "sys_pod"
        val DT_TEXT = "dt_txt"
        val ID_CITY = "city_id"

        val CREATE_TABLE_WEATHER = "CREATE TABLE " + TABLE_WEATHER + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DT + " TEXT," +
                TEMP + " TEXT," +
                TEMP_MIN + " REAL," +
                TEMP_MAX + " REAL," +
                PRESSURE + " TEXT," +
                SEA_LEVEL + " TEXT," +
                GRND_LEVEL + " TEXT," +
                HUMIDITY + " TEXT," +
                TEMP_KF + " REAL," +
                WEATHER_ID + " INTEGER," +
                WEATHER_MAIN + " TEXT," +
                WEATHER_DESCRIPTION + " TEXT," +
                WEATHER_ICON + " TEXT," +
                CLOUDS_ALL + " INTEGER," +
                WIND_SPEED + " REAL," +
                WIND_DEG + " INTEGER," +
                RAIN_3H+ " REAL,"+
                SYS_POD + " TEXT," +
                DT_TEXT + " TEXT," +
                ID_CITY +" INTEGER);"

        val CREATE_TABLE_CITY = "CREATE TABLE "+ TABLE_CITY+"("+
                ID +" INTEGER PRIMARY KEY,"+
                COD + " INTEGER,"+
                MESSAGE + " TEXT,"+
                CNT + " INTEGER,"+
                NAME + " TEXT,"+
                COORD_LAT + " REAL,"+
                COORD_LON +" REAL,"+
                COUNTRY +" TEXT,"+
                POPULATION +" TEXT,"+
                TIMEZONE +" TEXT,"+
                SUNRISE + " TEXT,"+
                SUNSET + " TEXT);"

    }

    interface Listener{
        fun onDataSaved(isSaved:Boolean)
    }

    override fun onSaveData(data: WeathersModel, listener: Listener) {
        val db = dataHelper.writableDatabase

        val values = ContentValues()
        values.put(ID, data.city!!.id)
        values.put(MESSAGE, data.message)
        values.put(COD, data.cod)
        values.put(CNT, data.cnt)
        values.put(NAME, data.city!!.name)
        values.put(COORD_LAT, data.city!!.coord.lat)
        values.put(COORD_LON, data.city!!.coord.lon)
        values.put(COUNTRY, data.city!!.country)
        values.put(POPULATION, data.city!!.population)
        values.put(TIMEZONE, data.city!!.timezone)
        values.put(SUNRISE, data.city!!.sunrise)
        values.put(SUNSET, data.city!!.sunset)

        val isCity = db.insert(TABLE_CITY, null, values)


        for(model in data.list!!){
            val value = ContentValues()
            value.put(DT, model.dt)
            value.put(TEMP, model.main.temp)
            value.put(TEMP_MIN, model.main.temp_min)
            value.put(TEMP_MAX, model.main.temp_max)
            value.put(PRESSURE, model.main.pressure)
            value.put(SEA_LEVEL, model.main.sea_level)
            value.put(GRND_LEVEL, model.main.grnd_level)
            value.put(HUMIDITY, model.main.humidity)
            value.put(TEMP_KF, model.main.temp_kf)
            value.put(WEATHER_ID, model.weather[0].id)
            value.put(WEATHER_MAIN, model.weather[0].main)
            value.put(WEATHER_DESCRIPTION, model.weather[0].description)
            value.put(WEATHER_ICON, model.weather[0].icon)
            value.put(CLOUDS_ALL, model.clouds.all)
            value.put(WIND_SPEED, model.wind.speed)
            value.put(SYS_POD, model.sys.pod)
            value.put(WIND_DEG, model.wind.deg)
            value.put(ID_CITY, isCity)
            try {
                /*
                * to handle blank object
                * */
                value.put(RAIN_3H, model.rain.h3)
            }catch (e:Exception){}
            value.put(DT_TEXT, model.dt_txt)

            db.insert(TABLE_WEATHER, null, value)
        }
        db.close()
        listener.onDataSaved(true)
    }

    override fun isAvailable(): Boolean {
        val db = dataHelper.writableDatabase
        val sql = "SELECT * FROM "+ TABLE_CITY+" LIMIT 1"
        val cursor = db.rawQuery(sql, null)
        if(cursor.moveToFirst()){
            return true
        }else{
            return false
        }
        db.close()
    }

    override fun getAllData(): WeathersModel {
        val db = dataHelper.readableDatabase
        val sql1 = "SELECT * FROM "+ TABLE_CITY
        val cursor1 = db.rawQuery(sql1, null)
        var data= WeathersModel()
        if (cursor1.moveToFirst()) {

            data.cod =cursor1.getInt(cursor1.getColumnIndex(COD))
            data.cnt = cursor1.getInt(cursor1.getColumnIndex(CNT))
            data.message =cursor1.getInt(cursor1.getColumnIndex(MESSAGE))
            data.city = City(
                cursor1.getInt(cursor1.getColumnIndex(ID)),
                cursor1.getString(cursor1.getColumnIndex(NAME)),
                Coord(cursor1.getDouble(cursor1.getColumnIndex(COORD_LAT)),
                    cursor1.getDouble(cursor1.getColumnIndex(COORD_LON))),
                cursor1.getString(cursor1.getColumnIndex(COUNTRY)),
                cursor1.getInt(cursor1.getColumnIndex(POPULATION)),
                cursor1.getInt(cursor1.getColumnIndex(TIMEZONE)),
                cursor1.getInt(cursor1.getColumnIndex(SUNRISE)),
                cursor1.getInt(cursor1.getColumnIndex(SUNSET)))


            val sql2 = "SELECT * FROM " + TABLE_WEATHER
            val cursor2 = db.rawQuery(sql2, null)
            val d = arrayListOf<ListsWeather>()

            if (cursor2.moveToFirst()) {
                do {
                    val main = Main(
                        cursor2.getDouble(cursor2.getColumnIndex(TEMP)),
                        cursor2.getDouble(cursor2.getColumnIndex(TEMP_MIN)),
                        cursor2.getDouble(cursor2.getColumnIndex(TEMP_MAX)),
                        cursor2.getInt(cursor2.getColumnIndex(PRESSURE)),
                        cursor2.getInt(cursor2.getColumnIndex(SEA_LEVEL)),
                        cursor2.getInt(cursor2.getColumnIndex(GRND_LEVEL)),
                        cursor2.getInt(cursor2.getColumnIndex(HUMIDITY)),
                        cursor2.getDouble(cursor2.getColumnIndex(TEMP_KF))
                    )

                    val weather = Weather(
                        cursor2.getInt(cursor2.getColumnIndex(WEATHER_ID)),
                        cursor2.getString(cursor2.getColumnIndex(WEATHER_MAIN)),
                        cursor2.getString(cursor2.getColumnIndex(WEATHER_DESCRIPTION)),
                        cursor2.getString(cursor2.getColumnIndex(WEATHER_ICON))
                    )
                    var rain: Rain
                    try {
                        rain = Rain(cursor2.getDouble(cursor2.getColumnIndex(RAIN_3H)))
                    } catch (e: Exception) {
                        rain = Rain(0.0)
                    }

                    val model = ListsWeather(
                        cursor2.getInt(cursor2.getColumnIndex(DT)),
                        main, arrayListOf(weather),
                        Clouds(cursor2.getInt(cursor2.getColumnIndex(CLOUDS_ALL))),
                        Wind(
                            cursor2.getDouble(cursor2.getColumnIndex(WIND_SPEED)),
                            cursor2.getInt(cursor2.getColumnIndex(WIND_DEG))
                        ),
                        rain,
                        Sys(cursor2.getString(cursor2.getColumnIndex(SYS_POD))),
                        cursor2.getString(cursor2.getColumnIndex(DT_TEXT))
                    )
                    d.add(model)
                } while (cursor2.moveToNext())
            }
            data.list = d

        }
        return data!!
    }

}