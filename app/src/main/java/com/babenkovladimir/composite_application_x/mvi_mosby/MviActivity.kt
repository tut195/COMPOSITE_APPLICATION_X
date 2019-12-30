package com.babenkovladimir.composite_application_x.mvi_mosby

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.babenkovladimir.composite_application_x.R
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_mvi.*
import kotlin.random.Random


class MviActivity : MviActivity<MviView, MainPresenter>(), MviView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvi)
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override
    fun render(viewState: MviViewState) {

        if (viewState.loading) {
            progress_bar.visibility = View.VISIBLE
            btn_question.isEnabled = false
            btn_answer.isEnabled = false
        } else if (viewState.error != null) {
            progress_bar.visibility = View.GONE
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        } else if (viewState.questionShown || viewState.answerShown) {
            progress_bar.visibility = View.GONE
            btn_question.isEnabled = true
            btn_answer.isEnabled = false
            text_view.text = viewState.textToShow
        } else {
            progress_bar.visibility = View.GONE
            btn_question.isEnabled = false
            btn_answer.isEnabled = true
            text_view.text = viewState.textToShow
        }
    }

    override fun askQuestionIntent(): Observable<Int?> {
        return RxView.clicks(btn_question).map { Random.nextInt() }

    }

    override fun getAnswerIntent(): Observable<Int?> {
        return RxView.clicks(btn_answer).map { Random.nextInt() }
    }

}
