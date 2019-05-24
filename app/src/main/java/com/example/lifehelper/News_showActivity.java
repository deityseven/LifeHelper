package com.example.lifehelper;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class News_showActivity extends AppCompatActivity {
    private Button button_news_show_to_main,
            button_news_show_delete,
            button_news_show_back;
    private String url;
    private int id;
    private WebView Mywebview;
    private Map<String, String> extraHeaders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_show);

        button_news_show_to_main = (Button)findViewById(R.id.button_news_show_finish) ;
        button_news_show_delete = (Button)findViewById(R.id.button_news_show_delete) ;
        button_news_show_back = (Button)findViewById(R.id.button_news_show_back) ;
        Mywebview = (WebView)findViewById(R.id.Mywebview) ;

        button_news_show_to_main.setOnClickListener(new MyListener_button_news_show_to_main());
        button_news_show_delete.setOnClickListener(new MyListener_button_news_show_delete());
        button_news_show_back.setOnClickListener(new MyListener_button_news_show_back());

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        id = intent.getIntExtra("id",-1);

        Mywebview.setWebViewClient(new WebViewClient(){});
        Mywebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }

        });
        Mywebview.getSettings().setJavaScriptEnabled(true);
        extraHeaders = new HashMap<String, String>();
        extraHeaders.put("device", "Android");//设备标识(前面是key，后面是value) extraHeaders.put("version", "1.0");//版本号(前面是key，后面是value)

        Mywebview.loadUrl(url);

    }
    class MyListener_button_news_show_to_main implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(News_showActivity.this, MainActivity.class);
            startActivity(intent); //执行
            finish();
        }
    }
    class MyListener_button_news_show_delete implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            DBHelper dbHelper1 = new DBHelper(News_showActivity.this, "LifeHelper_DB", null, 1);
            SQLiteDatabase db = dbHelper1.getWritableDatabase();
            db.delete("news", "id="+id, null);
            db.close();
            System.out.println("新闻记录删除成功");
            Intent intent = new Intent(News_showActivity.this, News_mainActivity.class);
            startActivity(intent); //执行
            finish();
        }
    }
    class MyListener_button_news_show_back implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(News_showActivity.this, News_mainActivity.class);
            startActivity(intent); //执行
            finish();
        }
    }
}