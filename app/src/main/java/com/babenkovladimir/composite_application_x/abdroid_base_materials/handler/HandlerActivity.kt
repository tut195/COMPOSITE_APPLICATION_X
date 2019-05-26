package com.babenkovladimir.composite_application_x.abdroid_base_materials.handler

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R

class HandlerActivity : AppCompatActivity() {

    // Variables

    lateinit var handler: Handler

    val MESSAGE_HACK_IN_PROGRESS = 0
    val MESSAGE_HACK_END = 1

    //Binding

    var etNeedToHack: EditText? = null
    var bStartHack: Button? = null
    var tvHackResult: TextView? = null
    var tvHackTime: TextView? = null
    var tvHackEndLabel: TextView? = null
    var rg: RadioGroup? = null
    var cbUseEngine: CheckBox? = null
    var pb: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        etNeedToHack = findViewById(R.id.etNeedToHack)
        bStartHack = findViewById(R.id.bStartHack)
        rg = findViewById(R.id.rgTask1)
        cbUseEngine = findViewById(R.id.cbUseEngine)
        tvHackResult = findViewById(R.id.tvHackResult)
        tvHackTime = findViewById(R.id.tvHackTime)
        tvHackEndLabel = findViewById(R.id.tvHackEndLabel)
        pb = findViewById(R.id.pb)


        handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)

                // первывй способ

                tvHackResult!!.text = msg!!.obj as String
                tvHackTime!!.text = " ${msg.arg1}  мс"


                // второй способ
//                val b = msg?.data
//                tvHackResult!!.text = b?.getString("text")
//                tvHackTime!!.text = " ${b?.getLong("time")}  мс."

                if (msg!!.what == MESSAGE_HACK_END) {
                    tvHackEndLabel!!.visibility = View.VISIBLE
                    pb?.visibility = View.INVISIBLE
                }
            }
        }
    }


    fun onClick(view: View) {
        val needToHackString = etNeedToHack!!.text.toString()
        pb?.visibility = View.VISIBLE

        when (rg!!.checkedRadioButtonId) {

            R.id.rbHandler -> {// Используется Handler
                val runnable = Runnable {
                    var indexHacking = 0
                    val stringBuilderHackResults = StringBuilder()
                    val timeStart = System.currentTimeMillis()


                    while (indexHacking < needToHackString.length) {
                        // проходимся по всей UNICODE-таблице
                        // и сравниваем каждый символ в ней
                        // с выбранным из строки

                        for (c in '\u0000'..'z') {
                            if (c == needToHackString[indexHacking]) {
                                Thread.sleep(500)
                                Log.e("TAG", "Подобрано : ${stringBuilderHackResults.append(c)} за ${System.currentTimeMillis() - timeStart} мс")
                                indexHacking++
                                break
                            }
                        }
                    }
                    Log.e("TAG", "Пароль взломан!!! - $stringBuilderHackResults за ${System.currentTimeMillis() - timeStart} мс")
                    runOnUiThread { pb!!.visibility = View.INVISIBLE }
                }

                Thread(runnable).start()
            }
            R.id.rbAsyncTask -> {
            }
        }
    }

    fun onClickHandler(view: View) {
        val needToHackString = etNeedToHack!!.text.toString()
        pb?.visibility = View.VISIBLE

        when (rg!!.checkedRadioButtonId) {
            R.id.rbHandler -> {// Используется Handler
                val runnable = Runnable {
                    var indexHacking = 0
                    val stringBuilderHackResults = StringBuilder()
                    val timeStart = System.currentTimeMillis()


                    while (indexHacking < needToHackString.length) {
                        // проходимся по всей UNICODE-таблице
                        // и сравниваем каждый символ в ней
                        // с выбранным из строки

                        for (c in '\u0000'..'z') {
                            if (c == needToHackString[indexHacking]) {
                                Thread.sleep(500)
                                val message = Message()

                                // Первый способ
                                message.obj = stringBuilderHackResults.append(c).toString()
                                message.arg1 = (System.currentTimeMillis() - timeStart).toInt()

                                // Второй способ

//                                val data = Bundle()
//                                data.putString("text", stringBuilderHackResults.append(c).toString())
//                                data.putLong("time", System.currentTimeMillis() - timeStart)

                                message.what = MESSAGE_HACK_IN_PROGRESS
                                handler.sendMessage(message)

                                Log.e("TAG", "Подобрано : $stringBuilderHackResults за ${System.currentTimeMillis() - timeStart} мс")
                                indexHacking++
                                break
                            }
                        }
                    }

                    val message = Message()
                    // первый способ
                    message.obj = stringBuilderHackResults.toString()
                    message.arg1 = (System.currentTimeMillis() - timeStart).toInt()
                    // Второй способ

//                    val data = Bundle()
//                    data.putString("text", stringBuilderHackResults.toString())
//                    data.putLong("time", System.currentTimeMillis() - timeStart)
//                    message.data = data

                    message.what = MESSAGE_HACK_END
                    handler.sendMessage(message)
                }

                Thread(runnable).start()
            }
            R.id.rbAsyncTask -> {
            }
        }
    }
}


