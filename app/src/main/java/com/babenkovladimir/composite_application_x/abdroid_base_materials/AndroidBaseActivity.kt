package com.babenkovladimir.composite_application_x.abdroid_base_materials

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.abdroid_base_materials.viewPager.ViewPagerActivity
import kotlinx.android.synthetic.main.activity_android_base.*

class AndroidBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_base)

        navigateViewPagerActivity.setOnClickListener { startActivity(Intent(this, ViewPagerActivity::class.java)) }
    }
}