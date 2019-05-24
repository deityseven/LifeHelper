package com.example.lifehelper;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Weather_cityActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText EditText_code,EditText_area;
    private ListView ListView_Area_code;
    private Button button_add_backmain,button_add_finish;

    private String phone_value,name,url,url_pis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_city);

        FindViewById();
        SetOnClickListener();
        SetOnItemClickListener();

    }
    private void SetOnItemClickListener() {
        ListView_Area_code.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DBHelper dbHelper1 = new DBHelper(Weather_cityActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db = dbHelper1.getReadableDatabase();

                Cursor cursor = db.query("area_code",null,null,null,null,null,null);

                List<String> lists_Weather_name = new ArrayList<String>();
                List<String> lists_Weather_url = new ArrayList<String>();
                List<Integer> lists_id = new ArrayList<Integer>();

                if (cursor.moveToFirst())
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String url = cursor.getString(cursor.getColumnIndex("url_pic"));
                        int id = cursor.getInt(cursor.getColumnIndex("id"));

                        lists_Weather_name.add(name);
                        lists_Weather_url.add(url);
                        lists_id.add(id);
                        System.out.println(name+" "+url+" "+id);
                    }while(cursor.moveToNext());

                System.out.println("ListView监听器绑定成功，当前点击位置："+i+" id ="+l);
                // 获取系统剪贴板
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
                ClipData clipData = ClipData.newPlainText(null, lists_Weather_url.get(i));
                // 把数据集设置（复制）到剪贴板
                clipboard.setPrimaryClip(clipData);
                Toast.makeText(Weather_cityActivity.this,"代码复制成功",Toast.LENGTH_SHORT).show();

                EditText_area.setText("");
                EditText_area.setText(lists_Weather_name.get(i));
                EditText_code.setText("");
                EditText_code.setText(lists_Weather_url.get(i));
                /*
                Intent intent = new Intent(Weather_cityActivity.this, Weather_showActivity.class);
                System.out.println("跳转执行完毕"+lists_Weather_url.get(i).toString());
                intent.putExtra("url", lists_Weather_url.get(i).toString());
                intent.putExtra("id", lists_id.get(i));
                startActivity(intent); //执行
                finish();
                System.out.println("跳转主页执行完毕");
                */
            }
        });
        DBHelper dbHelper1 = new DBHelper(Weather_cityActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db = dbHelper1.getReadableDatabase();

        Cursor cursor = db.query("area_code",null,null,null,null,null,null);
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

        ListView_Area_code.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                DBHelper dbHelper1 = new DBHelper(Weather_cityActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db = dbHelper1.getReadableDatabase();
                int memo_value = 0;

                Cursor cursor = db.query("area_code",null,null,null,null,null,null);
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
                TextView tv = new TextView(Weather_cityActivity.this);
                tv.setText(list.get(i).toString());
                tv.setTextSize(35);
                System.out.println(list.toString());
                return tv;
            }
        });
    }

    private void Datecheck() {
        if(name.equals("")){
            Toast.makeText(Weather_cityActivity.this,"地区名不能为空",Toast.LENGTH_SHORT).show();

        }else{
            if (url_pis.equals("")){
                Toast.makeText(Weather_cityActivity.this,"代码不能为空",Toast.LENGTH_SHORT).show();
            }else {
                Writedatebase();
            }
        }
    }

    private void Writedatebase() {
        DBHelper dbHelper = new DBHelper(Weather_cityActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone_value);
        values.put("url", url);
        db.insert("weather", null, values);
        IntentStart();
    }

    private void IntentStart() {
        Intent intent = new Intent(Weather_cityActivity.this, Weather_nationalActivity.class);
        startActivity(intent); //执行
        finish();
    }

    private void getDate() {
        DBHelper dbHelper = new DBHelper(Weather_cityActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor_get_phone = db.query("last_login",null,null,null,null,null,null);
        cursor_get_phone.moveToFirst();
        phone_value = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));
        name = EditText_area.getText().toString();
        url_pis = EditText_code.getText().toString();
        url = "http://m.weather.com.cn/mweather/"+url_pis+".shtml";/* 1dn/101050212 http://m.weather.com.cn/d/town/index?lat=47.35434&lon=123.95546*/
        db.close();
    }

    private void SetOnClickListener() {
        button_add_backmain.setOnClickListener(this);
        button_add_finish.setOnClickListener(this);
    }

    private void FindViewById() {
        EditText_code = (EditText)findViewById(R.id.EditText_code);
        EditText_area = (EditText)findViewById(R.id.EditText_area);
        ListView_Area_code = (ListView)findViewById(R.id.ListView_Area_code);
        button_add_backmain = (Button)findViewById(R.id.button_add_backmain);
        button_add_finish = (Button)findViewById(R.id.button_add_finish);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_add_backmain:
                Intent intent = new Intent(Weather_cityActivity.this, Weather_nationalActivity.class);
                startActivity(intent); //执行
                finish();
                break;
            case R.id.button_add_finish:
                getDate();
                Datecheck();
                break;
            default:
                break;
        }
    }

}