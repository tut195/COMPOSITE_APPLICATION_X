package com.babenkovladimir.composite_application_x.rx.rx_from_book

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.rx.rx_from_book.create.RxFromBookActivityCreate
import com.babenkovladimir.composite_application_x.rx.rx_from_book.filtering.RxFromBookFilteringActivity
import kotlinx.android.synthetic.main.activity_rx_from_book_dispatching.*

class RxFromBookDispatchingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_from_book_dispatching)

        rxCreate.setOnClickListener { startActivity(Intent(this, RxFromBookActivityCreate::class.java)) }
        rxFiltering.setOnClickListener { startActivity(Intent(this, RxFromBookFilteringActivity::class.java)) }

    }
}
