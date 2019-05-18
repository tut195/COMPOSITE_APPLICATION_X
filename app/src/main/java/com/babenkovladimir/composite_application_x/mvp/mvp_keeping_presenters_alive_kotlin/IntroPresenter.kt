package com.babenkovladimir.composite_application_x.mvp.mvp_keeping_presenters_alive_kotlin

class IntroPresenter : IntroMvmContract.Presenter {

    private var mView: IntroMvmContract.View? = null
    private var count: Int = 0

    // Life

    override fun attachView(view: IntroMvmContract.View) {
        mView = view
        updateView()
    }

    override fun detachView() {
        mView = null
    }

    override fun incrementValue() {
        count++
        updateView()
    }

    override fun decrementValue() {
        count--
        updateView()
    }

    // Private

    private fun updateView() {
        mView?.let { it.updateCount(count) }
    }
}