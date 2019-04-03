package com.example.mvp_practice.Model

import android.content.Context
import android.util.Log
import com.example.mvp_practice.Model.Weather.WeatherBean
import com.example.mvp_practice.Utils.HeSDKUtil
import com.example.mvp_practice.Utils.OkHttpUtil
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now

class WeatherModel: IWeatherModel {
    override fun getInfoBySDK(context: Context, iLoadListener: ILoadListener) {
        var resultCallback = object: HeSDKUtil.ResultCallback{
            override fun getWeather(now: Now) {
                iLoadListener.onSuccess(now)
            }

            override fun onFailure(msg: String) {
                iLoadListener.onFailure(msg)
            }
        }

        HeSDKUtil.getResultCallback(context,resultCallback)
    }

    override fun getInfo(url: String, iLoadListener: ILoadListener) {
        var resultCallback =object: OkHttpUtil.ResultCallback{
            override fun getWeather(weatherBean: WeatherBean) {
                iLoadListener.onSuccess(weatherBean)

            }

            override fun onFailure(e: Exception) {
                iLoadListener.onFailure(e)
            }

        }
        OkHttpUtil.getResultCallback(url,resultCallback)
    }


}