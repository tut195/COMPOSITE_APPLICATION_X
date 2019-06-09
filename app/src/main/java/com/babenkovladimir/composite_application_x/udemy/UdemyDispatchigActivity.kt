package com.babenkovladimir.composite_application_x.udemy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.udemy.services.background_services_demo.BackgroundServiceActivity
import com.babenkovladimir.composite_application_x.udemy.services.intent_service.IntentServiceActivity
import kotlinx.android.synthetic.main.activity_services_dispatchig.*

class UdemyDispatchigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services_dispatchig)

        navigateBackgroundServicesActivity.setOnClickListener { startActivity(Intent(this, BackgroundServiceActivity::class.java)) }
        navigateIntentServiceAcitivity.setOnClickListener { startActivity(Intent(this, IntentServiceActivity::class.java)) }


    }
}
