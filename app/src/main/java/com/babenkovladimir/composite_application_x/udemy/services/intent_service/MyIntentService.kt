package com.babenkovladimir.composite_application_x.udemy.services.intent_service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MyIntentService : IntentService("MyBackgroundThread") {

    companion object {
        private val TAG = MyIntentService::class.java.simpleName
    }

    // Optional
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate, Thread - ${Thread.currentThread().name}")
        Toast.makeText(this@MyIntentService, "StartExecution started", Toast.LENGTH_SHORT).show()
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent, Thread - ${Thread.currentThread().name}")
        // Write code here
        // Perform Long operation here!!!

        val sleepTime = intent?.getIntExtra("sleepTime", 1)!!

        var counter = 1
        while (counter <= sleepTime) {
            Log.d(TAG, "Time elapsed: $counter sec")

            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            counter++
        }

        // Сдесь необходимос создать объект localIntent

        val localIntent = Intent("my.own.broadcast")
        localIntent.putExtra("result", counter)
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent)
    }

    //Optional
    override fun onDestroy() {
        Toast.makeText(this@MyIntentService, "Execution is finished!!!", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onDestroy, Thread - ${Thread.currentThread().name}")
        super.onDestroy()
    }
}