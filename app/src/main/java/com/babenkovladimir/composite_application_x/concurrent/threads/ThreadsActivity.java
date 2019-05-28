package com.babenkovladimir.composite_application_x.concurrent.threads;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.babenkovladimir.composite_application_x.R;
import java.util.ArrayList;
import java.util.List;

public class ThreadsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_threads);

    // We will store the threads so that we can check if they are done
    List<Thread> threads = new ArrayList<Thread>();
    // We will create 500 threads
    for (int i = 0; i < 500; i++) {
      Runnable task = new MyRunnable(10000000L + i);
      Thread worker = new Thread(task);
      // We can set the name of the thread
      worker.setName(String.valueOf(i));
      // Start the thread, never call method run() direct
      worker.start();
      // Remember the thread for later usage
      threads.add(worker);
    }
    int running = 0;
    do {
      running = 0;
      for (Thread thread : threads) {
        if (thread.isAlive()) {
          running++;
        }
      }
      Log.d("RUNM", "We have " + running + " running threads. ");
    } while (running > 0);

  }
}
