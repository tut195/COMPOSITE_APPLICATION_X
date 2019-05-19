package com.babenkovladimir.composite_application_x.mvp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.mvp.moxy_x_implementation.moxyx.MoxyxActivityKotlin
import com.babenkovladimir.composite_application_x.mvp.mvp_keeping_presenters_alive_java.IntroActivity
import com.babenkovladimir.composite_application_x.mvp.mvp_keeping_presenters_alive_kotlin.SurvivingPresenterActivity
import kotlinx.android.synthetic.main.activity_mvp_nanigation.*

class MvpNavigatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp_nanigation)

        navigateMvpSurviveLifecycle.setOnClickListener { startActivity(Intent(this, SurvivingPresenterActivity::class.java)) }
        navigateMvpSurviveLifecycleJava.setOnClickListener { startActivity(Intent(this, IntroActivity::class.java)) }
        navigateMvpMoxyxKotlin.setOnClickListener { startActivity(Intent(this, MoxyxActivityKotlin::class.java)) }
    }
}
