package com.example.mvp_practice.Utils

import android.util.Log
import com.example.mvp_practice.Model.Weather.WeatherBean
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

const val TAG = "OkHttpUtil"

class OkHttpUtil {

     var res: String? = null

    companion object {
        private var okHttpUtil: OkHttpUtil? = null


        @Synchronized //添加synchronized修饰
        private fun getInstance(): OkHttpUtil{

            if (okHttpUtil == null) okHttpUtil = OkHttpUtil()
            return okHttpUtil!!
        }

        fun getResultCallback(url: String, resultCallback: ResultCallback){
            getInstance().sendRequest(url,resultCallback)
            Log.d(TAG,"getResultCallback")
        }



    }

    fun sendRequest(url: String,resultCallback: ResultCallback?){

        //新建一个OkHttpClient实例，用于处理请求
        var client = OkHttpClient.Builder()
            .connectTimeout(5,TimeUnit.SECONDS)
            .build()

        //构建请求参数
        val request: Request = Request.Builder()
            .url(url)
            .build()

        //生成一个具体请求实例
        var call = client.newCall(request)
        //call.enqueue()是异步方式； call.execute()是非异步方式会阻塞线程
            call.enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                    resultCallback?.onFailure(e)
            }

            @Throws (IOException::class)
            override fun onResponse(call: Call, response: Response) {
                res = response.body()?.string()

                var bean = GsonUtil.getWeather(res)
                    resultCallback?.getWeather(bean)
                Log.d(TAG,"sendRequest")

            }

        })
    }


     open interface ResultCallback{

       fun getWeather(weatherBean: WeatherBean)

       fun onFailure(e: Exception)
    }

}