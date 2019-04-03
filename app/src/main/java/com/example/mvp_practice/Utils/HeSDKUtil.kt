package com.example.mvp_practice.Utils

import android.content.Context
import android.util.Log
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now
import interfaces.heweather.com.interfacesmodule.view.HeWeather


class HeSDKUtil{

    companion object {
        private var heSDKUtil: HeSDKUtil? = null


            @Synchronized //添加synchronized修饰
            private fun getInstance(): HeSDKUtil{

                if (heSDKUtil == null) heSDKUtil = HeSDKUtil()
                return heSDKUtil!!
            }
        fun getResultCallback(context: Context, resultCallback: ResultCallback){
            getInstance().getData(context,resultCallback)
        }

    }


    fun getData(context: Context,resultCallback: ResultCallback?){

        HeWeather.getWeatherNow(context,"济南",object: HeWeather.OnResultWeatherNowBeanListener{
            override fun onSuccess(p0: MutableList<Now>) {
                Log.e("HeSDKUtil",p0[0].status)
                resultCallback?.getWeather(p0[0])

            }

            override fun onError(p0: Throwable) {
                Log.e("HeSDKUtil",p0.message.toString())
                resultCallback?.onFailure("请求参数错误或缺失")
            }

        })
    }

    open interface ResultCallback{
        fun getWeather(now: Now)
        fun onFailure(msg: String)

    }
}