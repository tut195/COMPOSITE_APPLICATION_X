package com.babenkovladimir.composite_application_x.concurrent.threads;

import android.util.Log;

public class MyRunnable implements Runnable {

  private final long countUntil;

  public MyRunnable(long countUntil) {
    this.countUntil = countUntil;
  }

  @Override
  public void run() {
    long sum = 0;
    for (long i = 1; i < countUntil; i++) {
      sum += i;
    }
    Log.d("RUN", "Sum = " + sum);
  }
}