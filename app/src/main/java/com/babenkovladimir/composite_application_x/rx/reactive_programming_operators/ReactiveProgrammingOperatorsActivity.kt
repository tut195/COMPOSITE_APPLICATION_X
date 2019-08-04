package com.babenkovladimir.composite_application_x.rx.reactive_programming_operators

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_reactivr_programming_operators.*

class ReactiveProgrammingOperatorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reactivr_programming_operators)

        btSchedulersExample.setOnClickListener { schedulersExample() }
        btSingleExample.setOnClickListener { singleExample() }
    }

    private fun schedulersExample() {

        val obsevable = Observable.just(1, 2, 3)

        obsevable
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    private fun singleExample() {
        Single.just("Hello World")
            .subscribe(getSingleObserver())
    }


    private val observer = object : Observer<Int> {
        override fun onComplete() {
            logprint("onComplete - All is done ${Thread.currentThread().name}")
        }

        override fun onSubscribe(d: Disposable) {
            logprint("onSubscribe - ${Thread.currentThread().name}")
        }

        override fun onNext(t: Int) {
            logprint("onNext $t - ${Thread.currentThread().name}")
        }

        override fun onError(e: Throwable) {
            logprint("on error - ${Thread.currentThread().name}")
        }
    }

    private fun getSingleObserver(): SingleObserver<String> {
        return object : SingleObserver<String> {
            override fun onSuccess(t: String) {
                logprint("on success $t")
            }

            override fun onSubscribe(d: Disposable) {
                logprint("on subscribe")
            }

            override fun onError(e: Throwable) {
                logprint("on error")
            }
        }
    }
}

fun logprint(message: String) {
    Log.d("TAGAAA", message)
}
