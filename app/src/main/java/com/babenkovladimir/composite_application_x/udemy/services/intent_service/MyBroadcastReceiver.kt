package com.babenkovladimir.composite_application_x.udemy.services.intent_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.babenkovladimir.composite_application_x.udemy.services.job_intent_service.MyJobIntentService

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent!!.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            // in tutorial this was crash post Oreo
//            val i = Intent(context, MyIntentService::class.java)
            val i = Intent(context, MyJobIntentService::class.java)
            i.putExtra("sleepTime", 12)

            MyJobIntentService.enqueueWork(context!!, i)
//            context!!.startService(i)
        }
    }
}