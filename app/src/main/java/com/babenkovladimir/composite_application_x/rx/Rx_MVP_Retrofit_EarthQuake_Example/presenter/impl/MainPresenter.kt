package com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.presenter.impl

import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model.entity.Feature
import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model.impl.EarthQuakerApi
import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.presenter.IMainPresenter
import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.view.IMainView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainPresenter(view: IMainView) : IMainPresenter {

    private val _earthquakeApi: EarthQuakerApi = EarthQuakerApi()
    private val _view: IMainView = view


    override fun getEarthquakesData(isUpdate: Boolean) {

        val dataObservable = _earthquakeApi.earthquakes

        //Really cool thing in RxAndroid is Schedulers. It helps us execute Network requests in new thread (subscribeOn)
        //and update our widgets in main thread with new data (observeOn)

        dataObservable
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { earthquakeData ->
                val earthquakes = mutableListOf<Feature>()
                earthquakes.addAll(earthquakeData.features)
                _view.hideLoadingIndicator()

                if (earthquakes.isEmpty()) {
                    _view.setEmptyResponseText("There is no earthquakes")
                } else if (isUpdate) {
                    _view.updateEarthquakesListView(earthquakes)
                } else {
                    _view.setEarthquakesListViewData(earthquakes)
                }
            }
    }
}