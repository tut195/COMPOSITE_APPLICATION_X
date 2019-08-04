package com.babenkovladimir.composite_application_x.rx.rx_kotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import kotlinx.android.synthetic.main.activity_rx_kotlin.*


class RxKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_kotlin)


        btStartStream.setOnClickListener { startStream() }
        btStartRStream.setOnClickListener { startRStream() }
        btStartZippedStream.setOnClickListener { startZippedStream() }
    }

    private fun startStream() {

        // Create an observable
        val myObservable = getObservable()

        // Create an observer
        val myObserver = getObserver()

        myObservable.subscribe(myObserver)

    }

    private fun startRStream() {
        val list = listOf("1", "2", "3", "4", "5", "6")

        // Apply to Observable extension function
        list.toObservable()
            //Construct your Observer using the subscribeBy() extension function//
            .subscribeBy(
                onNext = { logprint("Kotlin on next: $it") },
                onError = { it.printStackTrace() },
                onComplete = { logprint("on complete") }
            )
    }

    private fun startZippedStream() {
        val numbers = Observable.range(1, 6)
        val strings = Observable.just("One", "Two", "Three", "Four", "Five", "Six")

        val zipped = Observable.zip(strings, numbers, object : BiFunction<String, Int, String> {
            override fun apply(s: String, n: Int): String {
                return "$s $n"
            }
        })

        zipped.subscribe(::logprint)
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onComplete() {
                logprint("on complete")
            }

            override fun onSubscribe(d: Disposable) {
                logprint("on subscribe")
            }

            override fun onNext(t: String) {
                logprint("on next: $t")
            }

            override fun onError(e: Throwable) {
                logprint("on error: $e")
            }
        }
    }

    private fun getObservable(): Observable<String> {
        return Observable.just("1", "2", "3", "4", "5", "6")
    }
}

fun logprint(message: String) {
    Log.d("TAGAAA", message)
}