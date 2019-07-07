package com.babenkovladimir.composite_application_x.rx.rx_from_book.filtering

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import kotlinx.android.synthetic.main.activity_rx_from_book_filtering.*
import rx.Observable
import rx.functions.Func1
import java.util.concurrent.TimeUnit

class RxFromBookFilteringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_from_book_filtering)

        rxMap.setOnClickListener { map() }
        rxFlatMap.setOnClickListener { flatMap() }
        rxDelay.setOnClickListener { delay() }

    }


    private fun map() {
        val observable = Observable.from(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))

        val disposable = observable
            .map(object : Func1<Int, Int> {
                override fun call(t: Int?): Int {
                    return t!! * 2
                }
            })
            .subscribe {
                printMy(it)
            }
    }

    // Один из самых важный операторов в RxJava
    // На первый взгляд - выглядит как map, но в отличии но в результате преобразования каждого элемента может быть
    // возвращё ещё один (вложенный, внутренний) объект Observable. Может быть использован для запуска асинхронного вычисления
    // для каждого входнго события и последующего объединения результатов. Принимает объект типа Observable<T> и функцию, которая которая преобразует T в Observable<R> Результаты могут чередоваться!

    // Стоит использовать, если
    //  1) результат проеобразования map должен иметь тип Observable
    //  2) Преобразование имеет вид один ко многим - (поток заказчиков преобразуется в поток их заказов)

    private fun flatMap() {
        val observable = Observable.from(listOf(1, 2, 3, 4))

        observable
            .flatMap(object : Func1<Int, Observable<String>> {
                override fun call(t: Int?): Observable<String> {
                    return Observable.just((2 * t!!).toString(), (2 * t).toString())
                }
            })
            .subscribe(
                { printMy(it) }, {

                })

//        observable.flatMapIterable {
//
//        }
    }

    private fun delay() {
        val observable = Observable.from(listOf(1, 2, 3))
        observable
            .delay(2, TimeUnit.SECONDS)
            .subscribe {
                printMy(it)
            }

        Observable
            .timer(1, TimeUnit.SECONDS)
            .flatMap { i -> Observable.just(1, 2, 3) }
            .subscribe { printMy(it) }
    }

    private fun printMy(message: Any) {
        Log.d("TAGAAA", message.toString())
    }
}


