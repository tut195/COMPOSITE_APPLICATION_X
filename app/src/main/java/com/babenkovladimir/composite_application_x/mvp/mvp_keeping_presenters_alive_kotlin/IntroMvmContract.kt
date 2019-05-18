package com.babenkovladimir.composite_application_x.mvp.mvp_keeping_presenters_alive_kotlin

interface IntroMvmContract {

    interface View {
        fun updateCount(count: Int)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun incrementValue()
        fun decrementValue()
    }
}