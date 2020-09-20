package com.example.customview

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.layout.custom.CustomLayout
import com.example.customview.view.custom.OwnCustomView
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

// Custom View
//        val linearLayout = LinearLayout(this)
//        linearLayout.layoutParams = ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//        val customView = OwnCustomView(this)
//        customView.setTopLeftColor(BRIGHT_RED)
//        customView.setTopRightColor(BRIGHT_GREEN)
//        customView.setBottomLeftColor(BRIGHT_YELLOW)
//        customView.setBottomRightColor(BRIGHT_BLUE)
//        linearLayout.addView(customView)
//        setContentView(linearLayout)


//        setContentView(R.layout.activity_main_custom_layout)
//
//        val customLayout = findViewById<CustomLayout>(R.id.custom_layout)
//        val rnd = Random()
//        for (i in 0..49) {
//            val view = OwnCustomView(this)
//            val width: Int = rnd.nextInt(200) + 50
//            val height: Int = rnd.nextInt(100) + 100
//            view.layoutParams = ViewGroup.LayoutParams(width, height)
//            view.setPadding(20, 20, 20, 20)
//
//            customLayout.addView(view)
//        }

        // Rendering part

        setContentView(R.layout.activity_main_rendering)


    }

    companion object {
        private const val BRIGHT_GREEN = -0xff0100
        private const val BRIGHT_RED = -0x10000
        private const val BRIGHT_YELLOW = -0x100
        private const val BRIGHT_BLUE = -0xffff01
    }
}
