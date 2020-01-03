package com.babenkovladimir.composite_application_x.layout_manager;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.babenkovladimir.composite_application_x.R;
import com.babenkovladimir.composite_application_x.layout_manager.model.Article;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;


public class ArticleAdapter extends RecyclerView.Adapter {

  private static final String TAG = "ArticleAdapter";
  private static final String SCHEME_ASSETS = "assets://";
  private List<Article> articles;
  private OnItemClickListener itemClickListener;

  public OnItemClickListener getItemClickListener() {
    return itemClickListener;
  }

  public void setItemClickListener(OnItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
    return new ArticleViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
    ArticleViewHolder articleViewHolder = (ArticleViewHolder) viewHolder;
    Article article = articles.get(position);
    articleViewHolder.textContent.setText(article.text);
    articleViewHolder.textTitle.setText(article.title);
    if (article.image.startsWith(SCHEME_ASSETS)) {
      String fileName = article.image.replace(SCHEME_ASSETS, "");
      AssetManager assetManager = viewHolder.itemView.getContext().getAssets();
      InputStream is = null;
      try {
        is = assetManager.open(fileName);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        articleViewHolder.imageView.setImageBitmap(bitmap);
      } catch (IOException e) {
        Log.e(TAG, Log.getStackTraceString(e));
      } finally {
        IOUtils.closeQuietly(is);
      }
    }
    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (itemClickListener != null) {
          itemClickListener.onItemClicked(position);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return articles == null ? 0 : articles.size();
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
    notifyDataSetChanged();
  }

  public static class ArticleViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textContent;
    public TextView textTitle;

    public ArticleViewHolder(View itemView) {
      super(itemView);
      imageView = (ImageView) itemView.findViewById(R.id.article_image);
      textContent = (TextView) itemView.findViewById(R.id.article_text);
      textTitle = (TextView) itemView.findViewById(R.id.article_title);
    }
  }

  public interface OnItemClickListener {

    void onItemClicked(int pos);
  }
}