package com.weather.testapp.data.model
import com.google.gson.annotations.SerializedName


data class WeathersModel (

	@SerializedName("cod") val cod : Int,
	@SerializedName("message") val message : Int,
	@SerializedName("cnt") val cnt : Int,
	@SerializedName("list") val list : ArrayList<ListsWeather>,
	@SerializedName("city") val city : City
)