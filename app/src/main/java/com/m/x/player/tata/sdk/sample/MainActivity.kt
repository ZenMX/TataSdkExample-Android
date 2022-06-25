package com.m.x.player.tata.sdk.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.m.x.player.tata.sdk.MxSDK

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.start).setOnClickListener {
            MxSDK.testPlayback(this@MainActivity)
        }
    }
}