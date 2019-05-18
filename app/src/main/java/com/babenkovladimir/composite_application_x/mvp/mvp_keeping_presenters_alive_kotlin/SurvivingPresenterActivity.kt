package com.babenkovladimir.composite_application_x.mvp.mvp_keeping_presenters_alive_kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import kotlinx.android.synthetic.main.activity_survaving_presenter.*

class SurvivingPresenterActivity : AppCompatActivity(), IntroMvmContract.View {

    // Variables

    private var mPresenter: IntroMvmContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survaving_presenter)
        attachPresenter()
    }

    override fun onDestroy() {
        mPresenter?.detachView()
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return mPresenter
    }

    // IntroMvpContract.View implementation

    override fun updateCount(count: Int) {
        presenter_details.text = count.toString()
    }

    // Private

    private fun attachPresenter() {
        lastCustomNonConfigurationInstance?.let { mPresenter = it as IntroMvmContract.Presenter }

        if (mPresenter == null) {
            mPresenter = IntroPresenter()
        }

        mPresenter!!.attachView(this)
    }

    // onClick

    fun incrementButtonPressed(view: View) {
        mPresenter?.incrementValue()
    }

    fun decrementButtonPressed(view: View) {
        mPresenter?.decrementValue()
    }
}