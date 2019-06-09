package com.babenkovladimir.composite_application_x.udemy.services.job_intent_service

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R

class JobIntentServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_intent_service)
    }

    fun startJobIntentService(view: View) {
        val intent = Intent(this, MyJobIntentService::class.java)
        intent.putExtra("sleepTime", 12)

        MyJobIntentService.enqueueWork(this, intent)
    }
}
