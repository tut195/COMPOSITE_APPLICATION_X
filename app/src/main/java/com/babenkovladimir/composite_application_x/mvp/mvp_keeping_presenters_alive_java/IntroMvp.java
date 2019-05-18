package com.babenkovladimir.composite_application_x.mvp.mvp_keeping_presenters_alive_java;

interface IntroMvp {

  interface View {

    void updateCount(int count);
  }

  interface Presenter {

    void attachView(View view);

    void detachView();

    void incrementValue();

    void decrementValue();
  }
}
