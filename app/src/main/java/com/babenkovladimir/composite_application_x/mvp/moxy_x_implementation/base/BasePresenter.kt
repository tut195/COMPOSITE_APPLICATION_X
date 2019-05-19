package com.babenkovladimir.composite_application_x.mvp.moxy_x_implementation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>()