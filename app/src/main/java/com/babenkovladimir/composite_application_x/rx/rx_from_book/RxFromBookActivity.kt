package com.babenkovladimir.composite_application_x.rx.rx_from_book

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_rx_from_book.*

class RxFromBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_from_book)

        rxSyncCall.setOnClickListener { syncReactive() }

    }

    private fun syncReactive() {
        val observable = Observable.create<String>(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("the first emitted object")
                emitter.onNext("the second emitted object")
                emitter.onError(Throwable(message = "the throwable error"))
                emitter.onComplete()
            }
        })

        val disposable = observable.blockingSubscribe(
            object : Consumer<String> {
                override fun accept(t: String?) {
                    print(t)
                }
            },
            object : Consumer<Throwable> {
                override fun accept(t: Throwable?) {
                    print(t?.message)
                }
            }
        )
    }
}

// Extension

fun print(message: String?) {
    Log.d("TAGAAA", "message - $message")
}
