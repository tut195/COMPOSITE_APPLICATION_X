package com.babenkovladimir.composite_application_x.rx.rx_from_book.create

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_rx_from_book_create.*
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class RxFromBookActivityCreate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_from_book_create)

        rxSyncCall.setOnClickListener { syncReactive() }
        rxJust.setOnClickListener { just() }
        rxFrom.setOnClickListener { from() }
        rxRange.setOnClickListener { range() }
        rxEmpty.setOnClickListener { empty() }
        rxError.setOnClickListener { error() }
        rxTimer.setOnClickListener { timer() }
        rxInterval.setOnClickListener { iterval() }
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
                    printMy(t)
                }
            },
            object : Consumer<Throwable> {
                override fun accept(t: Throwable?) {
                    printMy(t?.message)
                }
            }
        )
    }

    // Creating Observables!
    // создаёт экземпляр Observable, который делает ровно одно значение value всем будущим подписчикам, а затем завершается.

    private fun just() {
        val just = Observable.just(
            "item 1",
            "item 2",
            "item 3",
            "item 4",
            "item 5",
            "item 6",
            "item 7",
            "item 8",
            "item 9",
            "item 10"
        )

        just.subscribe { it -> printMy(it) }
        printMy("secont subscribrer")
        just.subscribe { it -> printMy(it) }
    }

    // В книге метод from описан как просто from, в котлине его аналог есть fromIterable.
    // Есть аналогично just, но в качестве источника принимает объект типа Iterable<T> или T[] и следовательно
    // Создаёт столько элементов, сколько находится в массиве!
    // Перегшруженный вариант принимает аргумент типа Future

    private fun from() {
        val from = Observable.fromIterable(listOf("one", "two", "three"))
        val disposable = from.subscribe {
            printMy(it)
        }

        val future = object : Future<String> {
            override fun get(timeout: Long, unit: TimeUnit?): String {
                return "Hello From Future!!!"
            }

            override fun isDone(): Boolean {
                return true
            }

            override fun cancel(mayInterruptIfRunning: Boolean): Boolean {
                return false
            }

            override fun isCancelled(): Boolean {
                return false
            }

            override fun get(): String {
                return "Hello from future!!!"
            }
        }

        val fromFuture = Observable.fromFuture(future)
        val futureDisposable = fromFuture.subscribe({
            printMy(it)
        }, {

        })
    }

    // Порождает n целых чисел начиная с from. Любой подписчик получит один и тот же наор чисел
    // Почемуто не работает

    private fun range() {
        printMy("inside range!!!")
        val range = Observable.range(3, 7)

        val disposable = range.subscribe({
            printMy(it.toString())
        }, {

        }, {
            printMy("AAAA")

        })
    }

    // завершается сразу после подписки не порождая никаких значений

    private fun empty() {
        val empty = Observable.empty<String>()

    }

    // Не порождает вообще ничего. Может быть полезен для тестирования!

    private fun never() {
        val never = Observable.never<String>()

    }

    // Согласно контракту, уведомление onCompleted не может быть отправлено!

    private fun error() {
        val error = Observable.error<Exception>(Throwable(message = "Message from throwable"))


        val disposable = error.subscribe({}, {
            printMy(it.message)
        }, {})
    }

    //////////////////////////////////////////////////////////
    // Timer по существу это асинхронный эквивалент метода Thread.sleep.
    // Порождает нулевое значение типа Long по истечению задержки а потом завершается

    private fun timer() {
        val timer = Observable.timer(1, TimeUnit.SECONDS)

        timer.subscribe {
            printMy(it.toString())
        }
    }

    private fun iterval() {
        val interval = Observable.interval(1_000_000 / 60, TimeUnit.MICROSECONDS)
        interval.subscribe {
            printMy(it.toString())
        }
    }
}

// Extension

fun printMy(message: String?) {
    Log.d("TAGAAA", "message - $message")
}
