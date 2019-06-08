package com.babenkovladimir.composite_application_x.udemy.services.background_services_demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R


// Для этого примера необходимо испрользовать API 25 и ниже.
class BackgroundServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_service)
    }

    fun startBackgroundService(view: View) {
        val intent = Intent(this, MyBackgroundService::class.java)
        intent.putExtra("","")
        startService(intent)


    }

    fun stopBackgroundService(view: View) {
        val intent = Intent(this, MyBackgroundService::class.java)
        stopService(intent)

    }
}
