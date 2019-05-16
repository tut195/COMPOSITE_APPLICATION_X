package com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model;


import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model.entity.EarthquakeData;
import retrofit2.http.GET;
import rx.Observable;

public interface IEarthquakeService {

  //For simplification of this example, I use query parameters like this. Instead of query map.
  @GET("query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10")
  Observable<EarthquakeData> getEarthquakeData();

}
