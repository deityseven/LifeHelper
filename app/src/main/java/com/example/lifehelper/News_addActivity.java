package com.example.lifehelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class News_addActivity extends AppCompatActivity {
    private EditText editText_news_name,
            editText_news_url;
    private TextView textView4;
    private Button button_news_add_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_add);

        editText_news_name = (EditText)findViewById(R.id.editText_news_name);
        editText_news_url = (EditText)findViewById(R.id.editText_news_url);
        button_news_add_finish = (Button) findViewById(R.id.button_news_add_finish);
        textView4 = (TextView)findViewById(R.id.textView4);

        button_news_add_finish.setOnClickListener(new News_add_finish_listener());
    }
    class News_add_finish_listener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (!editText_news_name.getText().toString().equals("")){
                if (!editText_news_url.getText().toString().equals("")){
                    DBHelper dbHelper1 = new DBHelper(News_addActivity.this, "LifeHelper_DB", null, 1);
                    SQLiteDatabase db = dbHelper1.getReadableDatabase();

                    Cursor cursor_get_phone = db.query("last_login",null,null,null,null,null,null);
                    cursor_get_phone.moveToFirst();
                    String phone = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));
                    ContentValues values = new ContentValues();
                    values.put("name", editText_news_name.getText().toString());
                    values.put("phone", phone);
                    values.put("url", editText_news_url.getText().toString());
                    db.insert("news", null,values);
                    System.out.println("新闻记录表插入成功");
                    Intent intent = new Intent(News_addActivity.this, News_mainActivity.class);
                    startActivity(intent); //执行
                    System.out.println("跳转News_mian执行完毕");
                    finish();
                }else{
                    textView4.setText("api链接参数不能为空");
                }

            }else{
                textView4.setText("api名称不能为空");
            }

        }
    }
}