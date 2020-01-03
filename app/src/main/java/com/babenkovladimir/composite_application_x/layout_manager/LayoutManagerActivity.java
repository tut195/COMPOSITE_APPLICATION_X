package com.babenkovladimir.composite_application_x.layout_manager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;
import com.babenkovladimir.composite_application_x.R;
import com.babenkovladimir.composite_application_x.layout_manager.data.DataProvider;
import com.babenkovladimir.composite_application_x.layout_manager.data.FakeDataProvider;
import com.babenkovladimir.composite_application_x.layout_manager.model.Article;
import java.util.List;

public class LayoutManagerActivity extends AppCompatActivity {

  private DataProvider dataProvider;
  private AwesomeLayoutManager layoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layout_manager);

    final RecyclerView recyclerView = findViewById(R.id.recycler_view);
    layoutManager = new AwesomeLayoutManager(this);

    recyclerView.setLayoutManager(layoutManager);

    ArticleAdapter adapter = new ArticleAdapter();
    recyclerView.setAdapter(adapter);
    dataProvider = new FakeDataProvider(this);

    List<Article> articles = dataProvider.getArticles();
    adapter.setArticles(articles);

    recyclerView.setChildDrawingOrderCallback((childCount, i) -> childCount - i - 1);

    adapter.setItemClickListener(pos -> layoutManager.openItem(pos));

    findViewById(R.id.test_button).setOnClickListener(v -> recyclerView.smoothScrollToPosition(4));
  }

  @Override
  protected void onDestroy() {
    dataProvider = null;
    super.onDestroy();
  }

  @Override
  public void onBackPressed() {
    if (layoutManager.getOrientation() == AwesomeLayoutManager.Orientation.HORIZONTAL) {
      layoutManager.setOrientation(AwesomeLayoutManager.Orientation.VERTICAL);
    } else {
      super.onBackPressed();
    }
  }
}