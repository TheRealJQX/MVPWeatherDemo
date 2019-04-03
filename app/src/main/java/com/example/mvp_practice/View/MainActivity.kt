package com.example.mvp_practice.View

 import android.os.Bundle
 import android.util.Log
 import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.mvp_practice.Presenter.WeatherPresenter
 import com.example.mvp_practice.R
 import com.example.mvp_practice.Model.Weather.WeatherBean
 import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now
 import interfaces.heweather.com.interfacesmodule.view.HeConfig

/**
 * 项目名: MVPWeatherDemo
 * 描 述: 练习应用MVP架构、kotlin编程,以及练习使用Gson、OkHttp框架等。
 * 作 者: JQX
 * 时 间: 2019.4.2
 */
const val TAG = "MainActivity"

class MainActivity : BaseActivity() , IView{

    lateinit var bt_getDataBySDK: Button
    lateinit var bt_getData: Button
    lateinit var pb_dialog: ProgressBar
    lateinit var presenter: WeatherPresenter

    lateinit var tv_temperature: TextView
    lateinit var tv_city: TextView
    lateinit var tv_weather: TextView


    /*
     *初始化控件
     */
    override fun initViews() {
      tv_temperature = findViewById(R.id.tv_temperature)
      tv_city = findViewById(R.id.tv_city)
      tv_weather = findViewById(R.id.tv_weather)

      bt_getDataBySDK = findViewById(R.id.bt_getWeatherDataBySDK)
      bt_getData = findViewById(R.id.bt_getWeatherData)
      pb_dialog = findViewById(R.id.pb_weather)
    }

    /*
     *设置监听
     */
    override fun setListeners() {
      bt_getData.setOnClickListener {
          presenter.loadWeather()
      }

      bt_getDataBySDK.setOnClickListener {
          presenter.loadWeatherBySDK(this)
      }
    }

    /*
    *获取传递的数据
    */
    override fun getExtra() {
    }



    override fun showFailMessage(e: Exception) {
        runOnUiThread {
           Toast.makeText(this,"错误信息：$e",Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 显示通过和风WebAPI的方式得到的数据
     */
    override fun showDate(weatherBean: WeatherBean) {
        if((weatherBean.heWeather6[0]) !=null){
            var bean = weatherBean.heWeather6[0]
        runOnUiThread {
            if(bean.status=="ok") {
                tv_city.setText("城市：${bean.basic.location}")
                tv_temperature.setText("${bean.now.tmp}℃")
                tv_weather.setText("${bean.now.cond_txt}")

            }else Toast.makeText(this,"服务端错误${bean.status}",Toast.LENGTH_SHORT).show()
        }
        }else Toast.makeText(this,"信息解析失败",Toast.LENGTH_SHORT).show()
        Log.e(TAG,"showDate")
    }

    override fun showProgress() {
        if(pb_dialog.visibility== View.GONE)
            runOnUiThread {
                pb_dialog.visibility = View.VISIBLE
                Log.e(TAG, "showProgress")
            }
    }

    override fun closeProgress() {
        if(pb_dialog.visibility==View.VISIBLE)
            runOnUiThread {
                pb_dialog.visibility = View.GONE
            }
    }

    override fun showFailMessage(msg: String) {
        runOnUiThread {
            if (pb_dialog.visibility == View.VISIBLE)
                pb_dialog.visibility = View.GONE
        }
        Toast.makeText(this,"信息获取失败：$msg",Toast.LENGTH_SHORT).show()
    }

    /**
     * 显示通过和风SDK的方式得到的数据
     */
    override fun showDate(now: Now) {
        runOnUiThread {
         if(now.status=="ok") {
             tv_city.setText("城市：${now.basic.location}")
             tv_temperature.setText("${now.now.tmp}℃")
             tv_weather.setText("${now.now.cond_txt}")

         }else Toast.makeText(this,"服务端错误：${now.status}",Toast.LENGTH_SHORT).show()

        }

        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setListeners()
        presenter = WeatherPresenter(this)
        HeConfig.init("KEY的ID","KEY的值") //初始化和风账户
        HeConfig.switchToFreeServerNode() //设置为免费服务域名
    }
}
