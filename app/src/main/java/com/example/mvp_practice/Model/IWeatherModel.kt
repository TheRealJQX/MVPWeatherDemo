package com.example.mvp_practice.Model

import android.content.Context

interface IWeatherModel {

    //提供数据
    fun getInfo(url: String,iLoadListener: ILoadListener)

    //存储数据
    fun getInfoBySDK(context: Context,iLoadListener: ILoadListener)
}