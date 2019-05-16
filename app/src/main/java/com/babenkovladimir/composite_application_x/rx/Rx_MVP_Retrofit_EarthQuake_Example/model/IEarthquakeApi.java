package com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model;

import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model.entity.EarthquakeData;
import rx.Observable;

public interface IEarthquakeApi {

  Observable<EarthquakeData> getEarthquakes();
}