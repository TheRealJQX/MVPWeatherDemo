package com.example.mvp_practice.Presenter

import android.content.Context

interface IWeatherPresenter {

    fun loadWeather()
    fun loadWeatherBySDK(context: Context)
}