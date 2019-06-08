package com.babenkovladimir.composite_application_x.udemy.services.background_services_demo

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.lang.ref.WeakReference

class MyBackgroundService : Service() {

    companion object {
        val TAG = MyBackgroundService::class.simpleName
    }

    // Используется только когда мето создан и потом больше не используется
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "OnCreate, Thread name - ${Thread.currentThread().name}")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "OnStartCommand, Thread name - ${Thread.currentThread().name}")

        // Play the music
        // download file

        // Dummy long operation
        // As we can see, this operation is running on ui thread and freezes UI
        // So we are going to move this to asyncTask class
//        try {
//            Thread.sleep(12000)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

        MyAsyncTask(WeakReference(this)).execute()// Background Thread!

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG, "OnBind")
        return null
    }

    // Соответственно когда сервис уничтожается
    override fun onDestroy() {
        Log.i(TAG, "OnDestroy, Thread name - ${Thread.currentThread().name}")
        super.onDestroy()
    }

    // AsyncTaskClass

    class MyAsyncTask(private var serviceReference: WeakReference<MyBackgroundService>) : AsyncTask<Unit, String, Unit>() {

        // Live

        override fun onPreExecute() {
            super.onPreExecute()
            Log.i(TAG, "onPreExecute, Thread name - ${Thread.currentThread().name}")
        }

        // Perform tasks in Background or worker Thread
        override fun doInBackground(vararg params: Unit?): Unit {
            Log.i(TAG, "onDoInBackground, Thread name - ${Thread.currentThread().name}")

            // Dummy long operation

            var counter = 1
            while (counter <= 12) {
                publishProgress("Time elapsed: $counter secs")

                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                counter++
            }

            return Unit
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            Log.i(TAG, "onPostExecute, counter value = ${values[0]} Thread name - ${Thread.currentThread().name}")
            Toast.makeText(serviceReference.get(), values[0], Toast.LENGTH_SHORT).show()
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            serviceReference.get()?.stopSelf()
            Log.i(TAG, "onPostExecute, Thread name - ${Thread.currentThread().name}")
        }
    }
}