package com.babenkovladimir.composite_application_x.udemy.services.job_intent_service

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService

class MyJobIntentService : JobIntentService() {

    val TAG = MyJobIntentService::class.java.simpleName

    /**
     * Call this to enqueue work for your subclass of {@link JobIntentService}.  This will
     * either directly start the service (when running on pre-O platforms) or enqueue work
     * for it as a job (when running on O and later).  In either case, a wake lock will be
     * held for you to ensure you continue running.  The work you enqueue will ultimately
     * appear at {@link #onHandleWork(Intent)}.
     *
     * @param context Context this is being called from.
     * @param cls The concrete class the work should be dispatched to (this is the class that
     * is published in your manifest).
     * @param jobId A unique job ID for scheduling; must be the same value for all work
     * enqueued for the same class.
     * @param work The Intent of work to enqueue.
     */

    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, MyJobIntentService::class.java, 17, intent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate, Thread - ${Thread.currentThread().name}")
        Toast.makeText(this@MyJobIntentService, "StartExecution started", Toast.LENGTH_SHORT).show()
    }

    override fun onHandleWork(intent: Intent) {
        // Write your code here...
        Log.d(TAG, "onHandleWork, Thread - ${Thread.currentThread().name}")
        // Write code here
        // Perform Long operation here!!!

        val sleepTime = intent.getIntExtra("sleepTime", 1)

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
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy, Thread - ${Thread.currentThread().name}")
        Toast.makeText(this@MyJobIntentService, "task execution is finished!!!", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
}