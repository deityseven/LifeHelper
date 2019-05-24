package com.example.lifehelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class News_mainActivity extends AppCompatActivity {
    private ImageButton button_add;
    private Button button_news_main_backmain;
    private ListView listView_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);

        button_add = (ImageButton) findViewById(R.id.imageButton_news_add);
        listView_news = (ListView) findViewById(R.id.ListView_news);
        button_news_main_backmain = (Button) findViewById(R.id.button_news_main_backmain);

        button_add.setOnClickListener(new News_mainActivity.MyListener_ImageButton_news_add());
        button_news_main_backmain.setOnClickListener(new News_mainActivity.MyListener_button_news_main_backmain());

        listView_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DBHelper dbHelper1 = new DBHelper(News_mainActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db = dbHelper1.getReadableDatabase();

                Cursor cursor = db.query("news",null,null,null,null,null,null);

                List<String> lists_news_name = new ArrayList<String>();
                List<String> lists_news_url = new ArrayList<String>();
                List<Integer> lists_id = new ArrayList<Integer>();

                if (cursor.moveToFirst())
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String url = cursor.getString(cursor.getColumnIndex("url"));
                        int id = cursor.getInt(cursor.getColumnIndex("id"));

                        lists_news_name.add(name);
                        lists_news_url.add(url);
                        lists_id.add(id);
                        System.out.println(name+" "+url+" "+id);
                    }while(cursor.moveToNext());


                System.out.println("ListView监听器绑定成功，当前点击位置："+i+" id ="+l);
                Intent intent = new Intent(News_mainActivity.this, News_showActivity.class);
                System.out.println("跳转执行完毕"+lists_news_url.get(i).toString());
                intent.putExtra("url", lists_news_url.get(i).toString());
                intent.putExtra("id", lists_id.get(i));
                startActivity(intent); //执行
                finish();
                System.out.println("跳转主页执行完毕");
            }
        });

        DBHelper dbHelper1 = new DBHelper(News_mainActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db = dbHelper1.getReadableDatabase();

        Cursor cursor = db.query("news",null,null,null,null,null,null);
        //db.close();
        List<String> lists = new ArrayList<String>();

        if (cursor.moveToFirst())
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                lists.add(name);
                System.out.println(name);
            }while(cursor.moveToNext());

        redata(lists);

    }

    private void redata(final List list) {

        listView_news.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                DBHelper dbHelper1 = new DBHelper(News_mainActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db = dbHelper1.getReadableDatabase();
                int memo_value = 0;

                Cursor cursor = db.query("news",null,null,null,null,null,null);
                if (cursor.moveToFirst())
                    do {
                        memo_value += 1;
                    }while(cursor.moveToNext());

                System.out.println("读取数据库memo成功:"+memo_value);
                db.close();

                return memo_value;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView tv = new TextView(News_mainActivity.this);
                tv.setText(list.get(i).toString());
                tv.setTextSize(35);
                System.out.println(list.toString());
                return tv;
            }
        });
    }
    class MyListener_ImageButton_news_add implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(News_mainActivity.this, News_addActivity.class);
            startActivity(intent); //执行
            System.out.println("跳转注册页面执行完毕");
        }
    }
    class MyListener_button_news_main_backmain implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(News_mainActivity.this, MainActivity.class);
            startActivity(intent); //执行
            System.out.println("跳转注册页面执行完毕");
            finish();
        }
    }
}
