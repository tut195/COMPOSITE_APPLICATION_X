package com.babenkovladimir.composite_application_x.rx.rx_repository.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R

class RxRepositoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_repository)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frag_container, UsersListFragment()).commit()
        }
    }
}
// Working random Api!!!!
// https://randomapi.com/api/6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole
