package com.babenkovladimir.composite_application_x.udemy.services.background_services_demo;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class MyBackgroundServiceJ extends Service {

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }



   class MyAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
      stopSelf();
      return null;
    }
  }
}
