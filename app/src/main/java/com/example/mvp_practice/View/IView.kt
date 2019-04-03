package com.example.mvp_practice.View

import com.example.mvp_practice.Model.Weather.WeatherBean
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now

interface IView {

    //显示错误信息
    fun showFailMessage(e: Exception)
    fun showFailMessage(msg: String)
    //显示信息
    fun showDate(weatherBean: WeatherBean)
    fun showDate(now: Now)
    //显示加载框
    fun showProgress()

    //取消显示对话框
    fun closeProgress()
}