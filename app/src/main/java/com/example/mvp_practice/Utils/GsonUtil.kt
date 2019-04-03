package com.example.mvp_practice.Utils

import com.example.mvp_practice.Model.Weather.WeatherBean
import com.google.gson.Gson

/**
 * Gson工具类
 */
 class GsonUtil {

    companion object {
         fun getWeather(res: String?): WeatherBean =
            Gson().fromJson(res, WeatherBean::class.java)
    }
}