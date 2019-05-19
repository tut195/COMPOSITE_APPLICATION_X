package com.babenkovladimir.composite_application_x.mvp.moxy_x_implementation.moxyx

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface IMoxyxPresenter {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun incrementCount()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun decrementCount()
}