package com.babenkovladimir.composite_application_x.mvp.mvp_keeping_presenters_alive_java;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.babenkovladimir.composite_application_x.R;

public class IntroActivity extends AppCompatActivity implements IntroMvp.View {

  private TextView output;

  private IntroMvp.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro2);
    bindViews();
    attachPresenter();
  }

  private void attachPresenter() {
    presenter = (IntroMvp.Presenter) getLastCustomNonConfigurationInstance();
    if (presenter == null) {
      presenter = new IntroPresenter();
    }
    presenter.attachView(this);
  }

  @Override
  protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }

  @Override
  public Object onRetainCustomNonConfigurationInstance() {
    return presenter;
  }

  private void bindViews() {
    output = (TextView) findViewById(R.id.presenter_details);
  }

  public void incrementButtonPressed(View view) {
    presenter.incrementValue();
  }

  public void decrementButtonPressed(View view) {
    presenter.decrementValue();
  }

  @Override
  public void updateCount(final int count) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        output.setText("Counter: = " + count);
      }
    });
  }
}
