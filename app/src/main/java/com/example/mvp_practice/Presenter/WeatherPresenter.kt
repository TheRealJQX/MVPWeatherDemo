package com.example.mvp_practice.Presenter

import android.content.Context
import android.util.Log
import com.example.mvp_practice.Model.ILoadListener
import com.example.mvp_practice.Model.IWeatherModel
import com.example.mvp_practice.Model.WeatherModel
import com.example.mvp_practice.View.IView
import com.example.mvp_practice.Model.Weather.WeatherBean
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now
import java.lang.Exception

class WeatherPresenter: IWeatherPresenter, ILoadListener {

    private val KEY: String = "创建的应用所添加的密钥"
    private val CITY: String = "济南" //拼音汉字均可
    private val URL = "https://free-api.heweather.com/s6/weather/now?key=$KEY&location=$CITY"

    lateinit var mModel: IWeatherModel
    lateinit var mView: IView

    constructor(mView: IView) {
        this.mView = mView
        this.mModel = WeatherModel()
    }

    override fun loadWeather() {
        mView.showProgress()
        mModel.getInfo(URL,this)

    }
    override fun loadWeatherBySDK(context: Context) {
        mView.showProgress()
        mModel.getInfoBySDK(context,this)
    }


    override fun onSuccess(bean: WeatherBean) {
        mView.closeProgress()
        mView.showDate(bean)

    }

    override fun onFailure(e: Exception) {
        mView.closeProgress()
        mView.showFailMessage(e)
    }

    override fun onSuccess(now: Now) {
        mView.closeProgress()
        mView.showDate(now)
    }

    override fun onFailure(msg: String) {
        mView.closeProgress()
        mView.showFailMessage(msg)
    }



}

