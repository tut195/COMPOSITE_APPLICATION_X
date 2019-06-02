package com.babenkovladimir.composite_application_x.rx.rx_form_validation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_reactive_form_validation.*


class ReactiveFormValidationActivity : AppCompatActivity() {

    lateinit var observable: Observable<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.babenkovladimir.composite_application_x.R.layout.activity_reactive_form_validation)

        init()
    }

    private fun init() {

//        // Java version
//        Observable<String> nameObservable = RxTextView.textChanges(et_name).skip(1).map(new Function<CharSequence, String>() {
//            @Override
//            public String apply(CharSequence charSequence) throws Exception {
//                return charSequence.toString();
//            }
//        });

        val nameObservable = RxTextView
            .textChanges(et_name)
            .skip(1)
            .map(object : Function<CharSequence, String> {
                override fun apply(t: CharSequence): String {
                    return t.toString()
                }
            })

        val passwordObservable = RxTextView
            .textChanges(et_password)
            .skip(1)

            .map(object : Function<CharSequence, String> {
                override fun apply(t: CharSequence): String {
                    return t.toString()
                }
            })

        observable = Observable.combineLatest(nameObservable, passwordObservable, object : BiFunction<String, String, Boolean> {
            override fun apply(t1: String, t2: String): Boolean {
                return isValidForm(t1, t2)
            }
        })

        observable.subscribe(object : DisposableObserver<Boolean>() {
            override fun onComplete() {}

            override fun onNext(t: Boolean) {
                updateButton(t)
            }

            override fun onError(e: Throwable) {}
        })
    }

    fun isValidForm(name: String, password: String): Boolean {
        val validName = name.isNotEmpty() && name.length > 5

        if (!validName) {
            et_name.error = "Please enter valid name"
        }

        val validPass = password.isNotEmpty() && password.length > 5
        if (!validPass) {
            et_password.error = "Incorrect password"
        }
        return validName && validPass
    }

    fun updateButton(valid: Boolean) {
        if (valid)
            btn_login.isEnabled = true
    }
}
