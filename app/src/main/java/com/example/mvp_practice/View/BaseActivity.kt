package com.example.mvp_practice.View

import android.app.Activity

abstract class BaseActivity: Activity() {

   abstract fun initViews()
   abstract fun setListeners()
   abstract fun getExtra()
}