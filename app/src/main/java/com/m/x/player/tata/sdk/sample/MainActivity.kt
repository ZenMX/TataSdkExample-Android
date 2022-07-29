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

        findViewById<View>(R.id.start_1).setOnClickListener {
            MxSDK.startPlay()
                .withContent("4694edb9f78497662881d646798d5425", "tvshow_episode")
                .withToken("oV0rikNrPjlEqouQQNVLPl6UD7HcsTXL")
                .withDSN("004658356310")
                .withUserId("test-user-id")
                .play(this@MainActivity)
        }
    }
}