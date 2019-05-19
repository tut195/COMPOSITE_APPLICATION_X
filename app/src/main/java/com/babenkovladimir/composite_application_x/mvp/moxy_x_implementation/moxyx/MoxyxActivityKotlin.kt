package com.babenkovladimir.composite_application_x.mvp.moxy_x_implementation.moxyx

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.mvp.moxy_x_implementation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_moxyx.*

//class MoxyxActivityKotlin : AppCompatActivity() {

//class MoxyxActivityKotlin : MvpAppCompatActivity(), MoxyxView {
class MoxyxActivityKotlin : BaseActivity(), MoxyxView {

    @InjectPresenter
    internal lateinit var mPresenter: MoxyxPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moxyx)
    }

    override fun updateCount(count: Int) {
        presenter_details.text = "Count = ${count}"
    }

    fun incrementButtonPressed(view: View) {
        mPresenter.incrementCount()
    }

    fun decrementButtonPressed(view: View) {
        mPresenter.decrementCount()
    }
}
