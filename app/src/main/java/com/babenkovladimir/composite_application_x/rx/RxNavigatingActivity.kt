package com.babenkovladimir.composite_application_x.rx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.view.EarthQuakerActivity
import com.babenkovladimir.composite_application_x.rx.retrofit_rxjava2_kotlin.RetrofitRxJavaKotlinActivity
import com.babenkovladimir.composite_application_x.rx.rx_form_validation.ReactiveFormValidationActivity
import com.babenkovladimir.composite_application_x.rx.rx_repository.view.RxRepositoryActivity
import com.babenkovladimir.composite_application_x.rx.rxjava2_for_android_apps_RxBinding_and_RxLifecycle.RxBindingActivity
import com.babenkovladimir.composite_application_x.rx.rxjava_github.RxJavaGitHubActivity
import kotlinx.android.synthetic.main.activity_rx_navigating.*

class RxNavigatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_navigating)

        navigateEarthQuakeAppExample.setOnClickListener { startActivity(Intent(this@RxNavigatingActivity, EarthQuakerActivity::class.java)) }
        navigateRxRepositoryPattern.setOnClickListener { startActivity(Intent(this@RxNavigatingActivity, RxRepositoryActivity::class.java)) }
        navigateReactiveFormValidation.setOnClickListener { startActivity(Intent(this@RxNavigatingActivity, ReactiveFormValidationActivity::class.java)) }
        navigateRetrofitRxJavaKotlinActivity.setOnClickListener { startActivity(Intent(this@RxNavigatingActivity, RetrofitRxJavaKotlinActivity::class.java)) }
        navigate_RxBinding_RxLifecycle.setOnClickListener { startActivity(Intent(this@RxNavigatingActivity, RxBindingActivity::class.java)) }
        navigate_RxJava_GitHub_activity.setOnClickListener { startActivity(Intent(this@RxNavigatingActivity, RxJavaGitHubActivity::class.java)) }
    }
}
