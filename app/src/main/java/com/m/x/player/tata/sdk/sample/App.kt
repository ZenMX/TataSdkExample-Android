package com.m.x.player.tata.sdk.sample

import android.app.Application
import com.m.x.player.tata.sdk.MxSDK

class App : Application() {
    override fun onCreate() {
        super.onCreate()

//        MxSDK.Builder(this).debug(BuildConfig.DEBUG).verbose().build()
        MxSDK.Builder(this).debug(false).candidate(true).verbose().build()
    }
}