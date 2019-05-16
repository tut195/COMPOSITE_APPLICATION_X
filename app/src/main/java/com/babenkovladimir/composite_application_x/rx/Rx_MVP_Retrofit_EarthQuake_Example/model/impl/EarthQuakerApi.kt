package com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model.impl

import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model.IEarthquakeApi
import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model.IEarthquakeService
import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model.entity.EarthquakeData
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class EarthQuakerApi : IEarthquakeApi {

    override fun getEarthquakes(): Observable<EarthquakeData> {
        //Don't forget to add Adapters for RxJava and GsonConverter
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://earthquake.usgs.gov/fdsnws/event/1/")
            .build()

        val weatherService = retrofit.create(IEarthquakeService::class.java)

        return weatherService.earthquakeData
    }
}