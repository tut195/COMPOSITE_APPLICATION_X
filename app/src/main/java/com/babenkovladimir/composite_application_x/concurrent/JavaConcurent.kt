package com.babenkovladimir.composite_application_x.concurrent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.concurrent.callable.CallableActivity
import com.babenkovladimir.composite_application_x.concurrent.compleatable_future.CompletableFutureActivity
import com.babenkovladimir.composite_application_x.concurrent.executers.ExecutersActivity
import com.babenkovladimir.composite_application_x.concurrent.nonblocking_algorithm.NonblockingAlgorithmActivity
import com.babenkovladimir.composite_application_x.concurrent.threads.ThreadsActivity
import kotlinx.android.synthetic.main.activity_java_concurent.*

class JavaConcurent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_java_concurent)

        navigateJavaTrheads.setOnClickListener { startActivity(Intent(this, ThreadsActivity::class.java)) }
        navigateJavaExecuters.setOnClickListener { startActivity(Intent(this, ExecutersActivity::class.java)) }
        navigateJavaCallable.setOnClickListener { startActivity(Intent(this, CallableActivity::class.java)) }
        navigateCompletableFuteruActivity.setOnClickListener { startActivity(Intent(this, CompletableFutureActivity::class.java)) }
        navigateNonBlockingAlgorithm.setOnClickListener { startActivity(Intent(this, NonblockingAlgorithmActivity::class.java)) }
    }
}
