package com.babenkovladimir.composite_application_x.mvi_mosby;

import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class DataSource {

  public Observable<String> askQuestion(int questionId) {
    return Observable.just("Do you like Ice Cream?")
        .delay(3000, TimeUnit.MILLISECONDS);
  }

  public Observable<String> getAnswer(int questionId) {
    return Observable.just("Yes, I do!")
        .delay(3000, TimeUnit.MILLISECONDS);
  }

}
