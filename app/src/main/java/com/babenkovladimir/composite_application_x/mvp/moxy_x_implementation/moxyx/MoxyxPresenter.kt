package com.babenkovladimir.composite_application_x.mvp.moxy_x_implementation.moxyx

import com.arellomobile.mvp.InjectViewState
import com.babenkovladimir.composite_application_x.mvp.moxy_x_implementation.base.BasePresenter

@InjectViewState
class MoxyxPresenter : BasePresenter<MoxyxView>(), IMoxyxPresenter {

    private var count: Int = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateView()
    }

    override fun incrementCount() {
        count++
        updateView()
    }

    override fun decrementCount() {
        count--
        updateView()
    }

    private fun updateView() {
        viewState.updateCount(count)
    }
}