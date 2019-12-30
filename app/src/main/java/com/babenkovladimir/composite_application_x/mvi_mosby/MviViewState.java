package com.babenkovladimir.composite_application_x.mvi_mosby;

public class MviViewState {

  boolean loading;
  boolean questionShown;
  boolean answerShown;
  String textToShow;
  Throwable error;

  public MviViewState(boolean loading, boolean questionShown, boolean answerShown, String textToShow, Throwable error) {
    this.loading = loading;
    this.questionShown = questionShown;
    this.answerShown = answerShown;
    this.textToShow = textToShow;
    this.error = error;
  }
}
