package com.babenkovladimir.composite_application_x.rx.rxjava2_for_android_apps_RxBinding_and_RxLifecycle

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxTextView
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_rx_binding.*


class RxBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_binding)

        RxTextView.textChanges(editText)
            .subscribe { charSequence -> textView.text = charSequence }

        RxView.clicks(button)
            .subscribe { aVoid -> Toast.makeText(this@RxBindingActivity, "RxView.clicks", Toast.LENGTH_SHORT).show() }


        val rxPermission = RxPermissions(this)

        val subscription = rxPermission
            .request(Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS)
            .doOnNext { granted ->

                if (granted) {
                    // The permission is granted
                } else {
                    // Not granted
                }
            }
    }
}
