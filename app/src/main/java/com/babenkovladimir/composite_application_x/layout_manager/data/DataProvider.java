package com.babenkovladimir.composite_application_x.layout_manager.data;

import com.babenkovladimir.composite_application_x.layout_manager.model.Article;
import java.util.List;

public interface DataProvider {

  List<Article> getArticles();
}
