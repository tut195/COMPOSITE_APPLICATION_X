package com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.adapter.EarthquakesAdapter
import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model.entity.Feature
import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.presenter.IMainPresenter
import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.presenter.impl.MainPresenter
import kotlinx.android.synthetic.main.activity_eart_quaker.*

class EarthQuakerActivity : AppCompatActivity(), IMainView {


    private var _presenter: IMainPresenter? = null
    private var _adapter: EarthquakesAdapter? = null

    private var _earthquakes: List<Feature>? = null

    // Live

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eart_quaker)

        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            if (_presenter == null) _presenter = MainPresenter(this)

            _presenter?.getEarthquakesData(false)

            earthquakes_list_view.setOnItemClickListener { adapterView, view, i, l ->
                val url = _earthquakes!![i].properties.url
                val browseIntent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
                startActivity(browseIntent)
            }
        } else {
            showNoConnectionMessage()
        }


    }

    // Presenter implementation

    override fun setEarthquakesListViewData(earthquakes: List<Feature>) {
        _earthquakes = earthquakes
        _adapter = EarthquakesAdapter(applicationContext, _earthquakes)
        earthquakes_list_view.adapter = _adapter
    }

    override fun updateEarthquakesListView(earthquakes: List<Feature>) {
        _earthquakes = earthquakes
        _adapter?.notifyDataSetChanged()
    }

    override fun setEmptyResponseText(text: String) {
        oopsTextView.text = text
    }

    override fun hideLoadingIndicator() {
        loading_spinner.visibility = View.GONE
    }

    override fun showNoConnectionMessage() {
        loading_spinner.visibility = View.GONE
        oopsTextView.text = "No Internet Connection"
    }


}
