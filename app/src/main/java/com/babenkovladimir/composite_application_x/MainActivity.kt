package com.babenkovladimir.composite_application_x

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.rx.RxNavigatingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateRxNavigating.setOnClickListener { startActivity(Intent(this@MainActivity, RxNavigatingActivity::class.java)) }
    }
}
