package com.babenkovladimir.composite_application_x.mvi_mosby

import com.babenkovladimir.composite_application_x.mvi_mosby.PartialMainState.*
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter.ViewIntentBinder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter : MviBasePresenter<MviView, MviViewState>() {

    private val dataSource = DataSource()


    override fun bindIntents() {

        val askQuestion = intent<Int>(ViewIntentBinder<MviView, Int?> { obj: MviView -> obj.askQuestionIntent() })
            .switchMap { questionId: Int? ->
                dataSource.askQuestion(questionId!!)
                    .map { question: String? -> GotQuestion(question) as PartialMainState }
                    .startWith(Loading())
                    .onErrorReturn { error: Throwable? -> Error(error) }
                    .subscribeOn(Schedulers.io())
            }

        val getAnswer = intent<Int>(ViewIntentBinder<MviView, Int?> { obj: MviView -> obj.getAnswerIntent() })
            .switchMap { questionId: Int? ->
                dataSource.getAnswer(questionId!!)
                    .map { answer: String? -> GotAnswer(answer) as PartialMainState }
                    .startWith(Loading())
                    .onErrorReturn { error: Throwable? -> Error(error) }
                    .subscribeOn(Schedulers.io())
            }
        val initialState = MviViewState(false, false, false, "Ask Your question", null)

        val allIntents =
            Observable.merge(askQuestion, getAnswer)
                .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(
            allIntents.scan(
                initialState,
                { previousState: MviViewState, changedStatePart: PartialMainState? ->
                    viewStateReducer(
                        previousState,
                        changedStatePart
                    )
                }
            ), MviView::render
        )
    }

    fun viewStateReducer(previousState: MviViewState, changedStatePart: PartialMainState?): MviViewState {
        if (changedStatePart is Loading) {
            previousState.loading = true
        }
        if (changedStatePart is GotQuestion) {
            previousState.loading = false
            previousState.questionShown = true
            previousState.answerShown = false
            previousState.textToShow = changedStatePart.question
        }
        if (changedStatePart is GotAnswer) {
            previousState.loading = false
            previousState.questionShown = false
            previousState.answerShown = true
            previousState.textToShow = changedStatePart.answer
        }
        if (changedStatePart is Error) {
            previousState.loading = false
            previousState.error = changedStatePart.error
        }
        return previousState
    }
}