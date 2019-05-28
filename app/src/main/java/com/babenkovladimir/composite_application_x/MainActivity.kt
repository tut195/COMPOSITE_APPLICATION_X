package com.babenkovladimir.composite_application_x

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.abdroid_base_materials.AndroidBaseActivity
import com.babenkovladimir.composite_application_x.concurrent.JavaConcurent
import com.babenkovladimir.composite_application_x.mvp.MvpNavigatingActivity
import com.babenkovladimir.composite_application_x.rx.RxNavigatingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateRxNavigating.setOnClickListener { startActivity(Intent(this@MainActivity, RxNavigatingActivity::class.java)) }
        navigateMVPNvigating.setOnClickListener { startActivity(Intent(this@MainActivity, MvpNavigatingActivity::class.java)) }
        navigateAndroidBase.setOnClickListener { startActivity(Intent(this@MainActivity, AndroidBaseActivity::class.java)) }
        navigateJavaConcurrent.setOnClickListener { startActivity(Intent(this@MainActivity, JavaConcurent::class.java)) }
    }
}
