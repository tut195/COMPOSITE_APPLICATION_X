package com.babenkovladimir.composite_application_x

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.abdroid_base_materials.AndroidBaseActivity
import com.babenkovladimir.composite_application_x.concurrent.JavaConcurent
import com.babenkovladimir.composite_application_x.kotlin.KotlinNaviagationActivity
import com.babenkovladimir.composite_application_x.layout_manager.LayoutManagerActivity
import com.babenkovladimir.composite_application_x.mvi_mosby.MviActivity
import com.babenkovladimir.composite_application_x.mvp.MvpNavigatingActivity
import com.babenkovladimir.composite_application_x.rx.RxNavigatingActivity
import com.babenkovladimir.composite_application_x.services.ServicesDispatchingActivity
import com.babenkovladimir.composite_application_x.udemy.UdemyDispatchigActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateRxNavigating.setOnClickListener { startActivity(Intent(this@MainActivity, RxNavigatingActivity::class.java)) }
        navigateMVPNvigating.setOnClickListener { startActivity(Intent(this@MainActivity, MvpNavigatingActivity::class.java)) }
        navigateAndroidBase.setOnClickListener { startActivity(Intent(this@MainActivity, AndroidBaseActivity::class.java)) }
        navigateJavaConcurrent.setOnClickListener { startActivity(Intent(this@MainActivity, JavaConcurent::class.java)) }
        navigateUdemy.setOnClickListener { startActivity(Intent(this@MainActivity, UdemyDispatchigActivity::class.java)) }
        navigateKotlin.setOnClickListener { startActivity(Intent(this@MainActivity, KotlinNaviagationActivity::class.java)) }
        navigateServices.setOnClickListener { startActivity(Intent(this@MainActivity, ServicesDispatchingActivity::class.java)) }
        navigateMosbyMvi.setOnClickListener { startActivity(Intent(this@MainActivity, MviActivity::class.java)) }
        navigateLayoutManager.setOnClickListener { startActivity(Intent(this@MainActivity, LayoutManagerActivity::class.java)) }
    }
}
