package com.babenkovladimir.composite_application_x.concurrent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.concurrent.executers.ExecutersActivity
import com.babenkovladimir.composite_application_x.concurrent.threads.ThreadsActivity
import kotlinx.android.synthetic.main.activity_java_concurent.*

class JavaConcurent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_java_concurent)

        navigateJavaTrheads.setOnClickListener { startActivity(Intent(this, ThreadsActivity::class.java)) }
        navigateJavaExecuters.setOnClickListener { startActivity(Intent(this, ExecutersActivity::class.java)) }
    }
}
