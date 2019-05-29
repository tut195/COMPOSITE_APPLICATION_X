package com.babenkovladimir.composite_application_x.concurrent.compleatable_future;

import android.annotation.TargetApi;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.babenkovladimir.composite_application_x.R;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureActivity extends AppCompatActivity {

  @TargetApi(VERSION_CODES.N)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_compleatable_future);

    long started = System.currentTimeMillis();

    // configure CompletableFuture
    CompletableFuture<Integer> futureCount = createCompletableFuture();

    // continue to do other work
    Log.d("COMPLE", "Took " + (started - System.currentTimeMillis()) + " milliseconds");

    // now its time to get the result
    try {
      int count = futureCount.get();
      Log.d("COMPLE", "CompletableFuture took " + (started - System.currentTimeMillis()) + " milliseconds");

      Log.d("COMPLE", "Result " + count);
    } catch (InterruptedException | ExecutionException ex) {
      // Exceptions from the future should be handled here
    }

  }

  @RequiresApi(api = VERSION_CODES.N)
  private static CompletableFuture<Integer> createCompletableFuture() {
    CompletableFuture<Integer> futureCount = CompletableFuture.supplyAsync(
        () -> {
          try {
            // simulate long running task
            Thread.sleep(5000);
          } catch (InterruptedException e) {
          }
          return 20;
        });
    return futureCount;
  }
}