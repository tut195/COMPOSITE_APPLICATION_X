package com.babenkovladimir.composite_application_x.abdroid_base_materials

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.abdroid_base_materials.handler.HandlerActivity
import com.babenkovladimir.composite_application_x.abdroid_base_materials.recycler_view_diff_utils.RecyclerViewDiffUtilsActivity
import com.babenkovladimir.composite_application_x.abdroid_base_materials.retrofit.RetrofitActivity
import com.babenkovladimir.composite_application_x.abdroid_base_materials.sqlite.SqliteActivity
import com.babenkovladimir.composite_application_x.abdroid_base_materials.viewPager.ViewPagerActivity
import kotlinx.android.synthetic.main.activity_android_base.*

class AndroidBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_base)

        navigateViewPagerActivity.setOnClickListener { startActivity(Intent(this, ViewPagerActivity::class.java)) }
        navigateHandlerActivity.setOnClickListener { startActivity(Intent(this, HandlerActivity::class.java)) }
        navigateSqliteActivity.setOnClickListener { startActivity(Intent(this, SqliteActivity::class.java)) }
        navigateRetrofitIntro.setOnClickListener { startActivity(Intent(this, RetrofitActivity::class.java)) }
        navigateRecyclerViewDiffUtils.setOnClickListener { startActivity(Intent(this, RecyclerViewDiffUtilsActivity::class.java)) }
    }
}