package com.babenkovladimir.composite_application_x.mvi_mosby

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

// mvp view imported from
interface MviView : MvpView {

    fun askQuestionIntent(): Observable<Int?>

    fun getAnswerIntent(): Observable<Int?>

    fun render(viewState: MviViewState)
}