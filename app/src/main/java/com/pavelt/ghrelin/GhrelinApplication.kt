package com.pavelt.ghrelin

import android.app.Application
import android.content.Context

class GhrelinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        _appContext = this
    }

    companion object {

        private var _appContext: Context? = null
        val appContext get() = _appContext!!
    }
}