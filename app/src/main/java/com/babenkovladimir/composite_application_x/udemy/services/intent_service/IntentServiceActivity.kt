package com.babenkovladimir.composite_application_x.udemy.services.intent_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.babenkovladimir.composite_application_x.R
import kotlinx.android.synthetic.main.activity_inten_srvice.*

class IntentServiceActivity : AppCompatActivity() {

    // Variables

    private val myLocalBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent!!.getIntExtra("result", -1)
            txvResults.text = "Tasks executed in $result seconds"
        }
    }

    // Life

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inten_srvice)
    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter("my.own.broadcast")
        LocalBroadcastManager.getInstance(this).registerReceiver(myLocalBroadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myLocalBroadcastReceiver)
    }

    fun startIntentService(view: View) {
        val intent = Intent(this, MyIntentService::class.java)
        intent.putExtra("sleepTime", 12)

        startService(intent)
    }
}
