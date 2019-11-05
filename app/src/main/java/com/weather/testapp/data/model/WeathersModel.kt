package com.weather.testapp.data.model
import com.google.gson.annotations.SerializedName


class WeathersModel
{
	@SerializedName("cod")
	var cod : Int=0
	@SerializedName("message") var message : Int=0
	@SerializedName("cnt") var cnt : Int=0
	@SerializedName("list") var list : ArrayList<ListsWeather>?=null
	@SerializedName("city") var city : City?=null
}