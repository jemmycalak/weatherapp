package com.weather.testapp.data.local.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.weather.testapp.BuildConfig
import com.weather.testapp.app.di.scope.ContextQualifier
import com.weather.testapp.data.local.sql.weather.WeatherSQL
import javax.inject.Inject
import javax.inject.Singleton

class DataHelper
@Inject
constructor(context: Context?,
    name: String? = BuildConfig.DATABASE_NAME,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = BuildConfig.DATABASE_VERSION
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(WeatherSQL.CREATE_TABLE_WEATHER)
        db.execSQL(WeatherSQL.CREATE_TABLE_CITY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}