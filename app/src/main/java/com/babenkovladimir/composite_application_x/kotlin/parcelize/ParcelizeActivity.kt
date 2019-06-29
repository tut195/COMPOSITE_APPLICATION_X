package com.babenkovladimir.composite_application_x.kotlin.parcelize

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R

class ParcelizeActivity : AppCompatActivity() {

    private val simple = SimpleDataClass("Simple", 1)
    private val compound = CompoundDataClass("Compound", simple, "Transient")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelize)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_main, MainFragment.newInstance(simple, compound))
            commit()
        }
    }
}
