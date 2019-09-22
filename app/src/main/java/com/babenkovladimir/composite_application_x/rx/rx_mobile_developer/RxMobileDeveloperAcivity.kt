package com.babenkovladimir.composite_application_x.rx.rx_mobile_developer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.babenkovladimir.composite_application_x.R
import com.jakewharton.rxbinding.widget.RxTextView
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_mobile_developer_acivity.*
import java.util.concurrent.TimeUnit

class RxMobileDeveloperAcivity : AppCompatActivity() {

    private val disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_mobile_developer_acivity)
        val observable = Observable.just(1, 2, 3)

        val single = Single.just(1)
        val flowable = Flowable.just(1, 2, 3)


        btnTest.setOnClickListener {
            Log.e("TAG", "click")
        }

        // Материалы из первой лекции.

//        val sispose: Disposable = dataSourceFlowable()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                btnTest.setText("next int $it")
//
//                Log.e("TAG", it.toString())
//            }, {})


//        Handler().postDelayed({
//            result.dispose()
//            Log.e("TAGA", "disposed")
//        }, 4000)

        ///////////////////////////////////////////////////////////////////////

        // Вторая лекция

        simpleObservable()
//        resultMap()
//        resultFlatMap()
//        resultSwitchMap()
//        resultConcatMap()
//        resultBufferMap()
//        resultGroupBy()
//        resultScan()
//        resultDebounce()
//        resultSkip()

        // Лекция 3 - последнее видео по rxJava!!!
        // Комбинирование,
//        zip()
//        merge()
//        combineLatest()
//        concat()
        //  join()

        //----------------- Другте операторры

        // all()
        //containce()
        //delay()
        //count()
//        defaultIfEmpty()
//        timeInterval()
//        timestamp()
//        timeout()

        // ----------------- операторы do

        // doOperator()

        // ------------------ Rx binading

        rxBinding()


    }

    private fun rxBinding() {
        RxTextView.textChanges(editText)
            .debounce(500, TimeUnit.MILLISECONDS)
            .skip(3)
            .subscribe({

            }, {

            })
    }

    private fun doOperator() {
        // doOnNext - нуже чтобы более логирующие системные операции делать
        val names = Observable.just("Алексей", "Алексей", "Пётр!!!")

        val a = names
            .doOnNext { Log.e("TAGA", "do on next - ") }
            .doOnSubscribe { Log.e("TAGA", "do on subscriber - ") }
            .doOnComplete { Log.e("TAGA", "do on compleate - ") }
            .doOnError { Log.e("TAGA", "do on error - ") }
            .subscribe({

            }, {

            })
    }

    private fun timeout() {
        // чтото типа рабботы в реальном времени

        val names = Observable.just("Алексей", "Алексей", "Пётр!!!")

        val a = names
            .zipWith(Observable.interval(300, TimeUnit.MILLISECONDS), object : BiFunction<String, Long, String> {
                override fun apply(t1: String, t2: Long): String {
                    return "$t1, $t2"
                }
            })
            .timeout(310, TimeUnit.MILLISECONDS)
            .subscribe({
                Log.e("TAGA", "result value -> $it")
            }, {
                Log.e("TAGA", "error - ${it.localizedMessage}")
            })


    }

    private fun timestamp() {
        // не особо отличается от тайминтервала.
        val names = Observable.just("Алексей", "Алексей", "Пётр!!!")

        val a = names
            .timestamp()
            .subscribe({
                Log.e("TAGA", "result time -> ${it.time()}, value -> ${it.value()}")
            }, {

            })

    }

    private fun timeInterval() {

        // работает хорошо, если есть разница во времени
        val names = Observable.just("Алексей", "Алексей", "Пётр!!!")

        val a = names
            .zipWith(Observable.interval(300, TimeUnit.MILLISECONDS), object : BiFunction<String, Long, String> {
                override fun apply(t1: String, t2: Long): String {
                    return "$t1, $t2"
                }
            })
            .timeInterval()
            .subscribe({
                Log.e("TAGA", "result time -> ${it.time()}, value -> ${it.value()}")
            }, {

            })
    }

    private fun defaultIfEmpty() {
        val names = Observable.just("Алексей", "Алексей", "Хеллоу!!!")

        val a = names
            .skip(2)
            .defaultIfEmpty("User no found")
            .subscribe({
                Log.e("TAGA", "is empty??? = $it ")
            }, {

            })
    }

    private fun count() {
        val names = Observable.just("Алексей", "Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Олег")

        val a = names.count().subscribe({
            Log.e("TAGA", "сcount of names  should 7 = $it ")
        }, {

        })
    }

    private fun delay() {
        // Можно использовать для завершения анимации
        val names = Observable.just("Алексей", "Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Олег")
        val a =
            names
                .delay(3, TimeUnit.SECONDS)
                .subscribe({
                    Log.e("TAGA", "contains 3  - $it")
                }, {

                })
    }

    private fun containce() {
        val count = Observable.just(1, 2, 3, 4, 5, 6, 78, 8, 9)
        val a =
            count
                .contains(3)
                .subscribe({
                    Log.e("TAGA", "contains 3  - $it")
                }, {

                })
    }

    private fun all() {
        // После all превращается в single тиа boolean

        val names = Observable.just("Алексей", "Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Олег")
        val a =
            names
                .all(Predicate {
                    it.contains("и")
                })
                .subscribe({
                    Log.e("TAGA", "rezult - $it")
                }, {

                })
    }

    private fun resultSkip() {
        val resultSkip = Observable.just("Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Андрей")
            .debounce(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e("TAGA", "result - $result")

            }, {

            })
    }

    private fun resultDebounce() {
        val resultDebounce = Observable.just("Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Андрей")
            .debounce(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e("TAGA", "result - $result")

            }, {

            })
    }

    private fun resultScan() {
        val resultScan = Observable.just("Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Андрей")
            .scan(object : BiFunction<String, String, String> {
                override fun apply(t1: String, t2: String): String {
                    return "$t1 $t2"
                }
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e("TAGA", "result - $result")

            }, {

            })
    }

    private fun resultGroupBy() {
        val resultGroupBy = Observable.just("Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Андрей")
            .groupBy(object : io.reactivex.functions.Function<String, Boolean> {
                override fun apply(t: String): Boolean {
                    return t.toLowerCase().contains("а")
                }
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                if (result.key == true) {
                    val disp = result
                        .subscribeOn(Schedulers.newThread())
                        .subscribe({
                            Log.e("TAGA", "it-> $it")
                        }, {})

                    Log.e("TAGA", "it ${result}")
                }

            }, {

            })
    }

    private fun resultBufferMap() {
        val resultBufferMap = Observable.just("Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Андрей")
            .buffer(2)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("TAGA", "count->  ${it.count()}")

                it.forEach { item ->
                    Log.e("TAGA", "it $item")
                }

            }, {

            })
    }

    fun simpleObservable() {
        val result: Disposable = Observable.just("1", "2", "3", "4", "5")
            .delay(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("TAGA", "value is $it")
            }, {

            })
    }

    fun resultMap() {
        val resultMap = Observable.just("Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Андрей")
            .map { it + " 30" }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("TAGA", "it is $it")
            }, {

            })
    }

    private fun resultFlatMap() {
        val resultFlatMap = Observable.just("Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Андрей")
            .flatMap {
                val delay = java.util.Random().nextInt(10)
                Log.e("TAGA", "delay $delay")
                Observable.just(it).delay(delay.toLong(), TimeUnit.SECONDS)
            }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("TAGA", "it is $it")
            }, {

            })
    }

    private fun resultSwitchMap() {
        // В данный момент switch map сделает какието операции только с полследним элемиентом
        val resultSwitchMap = Observable.just("Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Андрей")
            .switchMap {
                val delay = java.util.Random().nextInt(10)
                Log.e("TAGA", "delay $delay")
                Observable.just(it).delay(delay.toLong(), TimeUnit.SECONDS)
            }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("TAGA", "it is $it")
            }, {

            })
    }

    private fun resultConcatMap() {
        val resultConcatMap = Observable.just("Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Андрей")
            .concatMap {
                val delay = java.util.Random().nextInt(10)
                Log.e("TAGA", "delay $delay")
                Observable.just(it).delay(delay.toLong(), TimeUnit.SECONDS)
            }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("TAGA", "it is $it")
            }, {

            })
    }

    private fun zip() {
        val names = Observable.just("Алексей", "Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Олег")
        val surnames = Observable.just("Иванов", "Петров", "Сидоров", "Цветков", "Никулин", "Миронов", "Попанов")

        disposeBag.add(names.zipWith(surnames, object : BiFunction<String, String, String> {
            override fun apply(t1: String, t2: String): String {
                return "$t1 $t2"
            }
        }).subscribe {
            Log.e("TAGA", "result $it")
        })
    }

    // Мердже не заботится о порядке.
    private fun merge() {
        val names = Observable.just("Алексей", "Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Олег")
        val surnames = Observable.just("Иванов", "Петров", "Сидоров", "Цветков", "Никулин", "Миронов", "Попанов")


        disposeBag.add(
            names.zipWith(Observable.interval(300, TimeUnit.MILLISECONDS), object : BiFunction<String, Any, String> {
                override fun apply(t1: String, t2: Any): String {
                    return t1
                }
            }).mergeWith(
                surnames.zipWith(
                    Observable.interval(500, TimeUnit.MILLISECONDS),
                    object : BiFunction<String, Any, String> {
                        override fun apply(t1: String, t2: Any): String {
                            return t1
                        }
                    })
            ).subscribe({
                Log.e("TAGA", "result -> $it")
            }, {

            })
        )

    }

    // Практически ничем не отличается от merge - только он дожидаетсяя выполнения одного, и только потом выводит второй
    private fun concat() {
        val names = Observable.just("Алексей", "Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Олег")
        val surnames = Observable.just("Иванов", "Петров", "Сидоров", "Цветков", "Никулин", "Миронов", "Попанов")

        disposeBag.add(
            names.zipWith(Observable.interval(300, TimeUnit.MILLISECONDS), object : BiFunction<String, Any, String> {
                override fun apply(t1: String, t2: Any): String {
                    return t1
                }
            }).concatWith(
                surnames.zipWith(
                    Observable.interval(500, TimeUnit.MILLISECONDS),
                    object : BiFunction<String, Any, String> {
                        override fun apply(t1: String, t2: Any): String {
                            return t1
                        }
                    })
            ).subscribe({
                Log.e("TAGA", "result -> $it")
            }, {

            })
        )
    }

    private fun combineLatest() {
        val temperatureFactotyOne = Observable.just(120, 111, 90, 100, 102)
        val temperatureFactotyTwo = Observable.just(-10, -20, -5, -30)

        val disposable = Observable.combineLatest(
            temperatureFactotyOne.zipWith(
                Observable.interval(300, TimeUnit.MILLISECONDS),
                object : BiFunction<Int, Any, Int> {
                    override fun apply(t1: Int, t2: Any): Int {
                        return t1
                    }
                }),
            temperatureFactotyTwo.zipWith(
                Observable.interval(500, TimeUnit.MILLISECONDS), object : BiFunction<Int, Any, Int> {
                    override fun apply(t1: Int, t2: Any): Int {
                        return t1
                    }
                }), object : BiFunction<Int, Int, Array<Int>> {
                override fun apply(t1: Int, t2: Int): Array<Int> {
                    return arrayOf(t1, t2)
                }
            }
        ).subscribe({
            try {
                Log.e("TAGA", "factory one - ${it[0]}, factory two - ${it[1]}")
            } catch (e: Exception) {
                Log.e("TAGA", "error - ${e.localizedMessage}")
            }
        }, {

        })
    }

    // Нетривиальный оператор. В нем есть смысл только во времени. Иначе concat
    private fun join() {
        val left = Observable.interval(100, TimeUnit.MILLISECONDS)
        val right = Observable.interval(300, TimeUnit.MILLISECONDS)


        val dispose = left.join(right,
            object : io.reactivex.functions.Function<Long, Observable<Long>> {
                override fun apply(t: Long): Observable<Long> {
                    return Observable.timer(300, TimeUnit.MILLISECONDS)
                }

            }, object : io.reactivex.functions.Function<Long, Observable<Long>> {
                override fun apply(t: Long): Observable<Long> {
                    return Observable.timer(100, TimeUnit.MILLISECONDS)
                }
            }, object : BiFunction<Long, Long, Long> {
                override fun apply(t1: Long, t2: Long): Long {
                    Log.e("TAGA", "left $t1 right $t2")
                    return t1 + t2
                }
            })
            .take(10)
            .subscribe({
                Log.e("TAGA", "result -> $it")
            }, {
                Log.e("TAGA", "Error - ${it.localizedMessage}")
            })
    }

    // Работают с ним редко. как точко появляется новый поток данных, прекращает работать со старым и начинает с новым.
    private fun switchOnNext() {
        val names = Observable.just("Алексей", "Алексей", "Владимир", "Георгий", "Дмитрий", "Евгений", "Олег")
            .zipWith(Observable.interval(100, TimeUnit.MILLISECONDS), object : BiFunction<String, Any, String> {
                override fun apply(t1: String, t2: Any): String {
                    return t1
                }
            })

        val surnames = Observable.just("Иванов", "Петров", "Сидоров", "Цветков", "Никулин", "Миронов", "Попанов")
            .zipWith(Observable.interval(500, TimeUnit.MILLISECONDS), object : BiFunction<String, Any, String> {
                override fun apply(t1: String, t2: Any): String {
                    return t1
                }
            })

        val a = Observable.switchOnNext(Observable.timer(100L, TimeUnit.MILLISECONDS).map { l -> names }
            .concatWith(Observable.timer(300L, TimeUnit.MILLISECONDS).map { l -> surnames }))
            .subscribe({
                Log.e("TAGA", "result -> $it")
            })

    }


    override fun onDestroy() {
        disposeBag.clear()
        super.onDestroy()
    }

    fun dataSource(): Observable<Int> {
        return Observable.create { subscriber ->
            for (i in 0..100) {
                Thread.sleep(1000)
                subscriber.onNext(i)
            }
        }
    }

    fun dataSourceFlowable(): Flowable<Int> {
        return Flowable.create({ subscriber ->
            for (i in 0..90000) {
                subscriber.onNext(i)
            }
            subscriber.onComplete()
        }, BackpressureStrategy.LATEST)

    }

    // Максимально приближенный к колбэку вариант
    fun dataSourceSingle(): Single<List<Int>> {
        return Single.create { subscriber ->
            val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
            subscriber.onSuccess(list)
        }
    }

    fun dataSourceCompletable(): Completable {
        return Completable.create { subscriber ->
            subscriber.onComplete()
        }
    }

    fun dataSourceMaybe(): Maybe<List<Int>> {
        return Maybe.create { subscriber ->
            val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
            subscriber.onSuccess(list)
            subscriber.onComplete()
        }
    }
}