package com.babenkovladimir.composite_application_x.kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.kotlin.parcelize.ParcelizeActivity
import kotlinx.android.synthetic.main.activity_kotlin_naviagation.*

class KotlinNaviagationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_naviagation)

        navigateParcelize.setOnClickListener { startActivity(Intent(this, ParcelizeActivity::class.java)) }
    }
}
