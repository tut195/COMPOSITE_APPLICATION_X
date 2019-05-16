package com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.view;


import com.babenkovladimir.composite_application_x.rx.Rx_MVP_Retrofit_EarthQuake_Example.model.entity.Feature;
import java.util.List;

public interface IMainView {

  void setEarthquakesListViewData(List<Feature> earthquakes);

  void updateEarthquakesListView(List<Feature> earthquakes);

  void setEmptyResponseText(String text);

  void hideLoadingIndicator();

  void showNoConnectionMessage();

}
