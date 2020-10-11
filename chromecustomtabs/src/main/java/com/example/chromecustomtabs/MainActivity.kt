package com.example.chromecustomtabs

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val url = "https://paul.kinlan.me/"

        val builder = CustomTabsIntent.Builder()


        //customTabsIntent.launchUrl(this, Uri.parse(url))


        //Configure the color of the address bar
        val colorInt = Color.parseColor("#FF0000") //red
        builder.setToolbarColor(colorInt)

//        Configure a custom action button

        //builder.setActionButton(icon, description, pendingIntent, tint);


        val customTabsIntent = builder.build()
        chrmotebasBtn.setOnClickListener {
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }

    }
}
