package com.example.mvp_practice.Model

import com.example.mvp_practice.Model.Weather.WeatherBean
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now
import java.lang.Exception

interface ILoadListener {

    fun onSuccess(bean: WeatherBean)

    fun onFailure(e: Exception)

    fun onSuccess(now: Now)

    fun onFailure(msg: String)
}