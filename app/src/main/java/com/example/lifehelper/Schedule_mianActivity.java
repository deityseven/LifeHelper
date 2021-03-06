package com.example.lifehelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Schedule_mianActivity extends AppCompatActivity {
    private ImageButton button_add;
    private ListView listView_schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_mian);

        button_add = (ImageButton) findViewById(R.id.imageButton_schedule_add);
        listView_schedule = (ListView) findViewById(R.id.ListView_schedule);

        button_add.setOnClickListener(new MyListener_ImageButton_schedule_add());

        listView_schedule.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DBHelper dbHelper1 = new DBHelper(Schedule_mianActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db = dbHelper1.getReadableDatabase();

                Cursor cursor = db.query("schedule",null,null,null,null,null,null);

                List<String> lists_id = new ArrayList<String>();
                List<String> lists_phone = new ArrayList<String>();
                List<String> lists_content = new ArrayList<String>();
                List<String> lists_date = new ArrayList<String>();
                List<Integer> lists_remindDate = new ArrayList<Integer>();
                List<Integer> lists_repeat = new ArrayList<Integer>();
                List<String> lists_memo = new ArrayList<String>();
                List<String> lists_place = new ArrayList<String>();



                if (cursor.moveToFirst())
                    do {
                        String id = cursor.getString(cursor.getColumnIndex("id"));
                        String phone = cursor.getString(cursor.getColumnIndex("phone"));
                        String content = cursor.getString(cursor.getColumnIndex("content"));
                        String date = cursor.getString(cursor.getColumnIndex("date"));
                        int remindDate = cursor.getInt(cursor.getColumnIndex("remindDate"));
                        int repeat = cursor.getInt(cursor.getColumnIndex("repeat"));
                        String memo = cursor.getString(cursor.getColumnIndex("memo"));
                        String place = cursor.getString(cursor.getColumnIndex("place"));



                        lists_phone.add(phone);
                        lists_content.add(content);
                        lists_date.add(date);
                        lists_id.add(id);
                        lists_remindDate.add(remindDate);
                        lists_repeat.add(repeat);
                        lists_memo.add(memo);
                        lists_place.add(place);
                        System.out.println(content+" "+date+" "+id);
                    }while(cursor.moveToNext());


                System.out.println("ListView监听器绑定成功，当前点击位置："+i+" id ="+l);
                Intent intent = new Intent(Schedule_mianActivity.this, Schedule_changeActivity.class);
                intent.putExtra("old_content", lists_content.get(i).toString());
                intent.putExtra("old_date", lists_date.get(i).toString());
                intent.putExtra("phone", lists_phone.get(i).toString());
                intent.putExtra("id", lists_id.get(i).toString());
                intent.putExtra("remindDate", lists_remindDate.get(i));
                intent.putExtra("repeat", lists_repeat.get(i));
                intent.putExtra("memo", lists_memo.get(i).toString());
                intent.putExtra("place", lists_place.get(i).toString());
                startActivity(intent); //执行
                System.out.println("跳转主页执行完毕");
            }
        });

        DBHelper dbHelper1 = new DBHelper(Schedule_mianActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db = dbHelper1.getReadableDatabase();

        Cursor cursor = db.query("schedule",null,null,null,null,null,null);
        //db.close();
        List<String> lists = new ArrayList<String>();

        if (cursor.moveToFirst())
            do {
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                lists.add(content+" "+date);
                System.out.println(content+" "+date);
            }while(cursor.moveToNext());
            /*
        TextView textView1 = new TextView(Memo_mianActivity.this);
        textView1.setText(content+i);
        textView1.setTextSize(24);

        return textView1;
            */

        redata(lists);
        //listView_memo.setAdapter(new MyAdapter());


    }

    private void redata(final List list) {

        listView_schedule.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                DBHelper dbHelper1 = new DBHelper(Schedule_mianActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db = dbHelper1.getReadableDatabase();
                int memo_value = 0;

                Cursor cursor = db.query("schedule",null,null,null,null,null,null);
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
                TextView tv = new TextView(Schedule_mianActivity.this);
                tv.setText(list.get(i).toString());
                tv.setTextSize(35);
                System.out.println(list.toString());
                return tv;
            }
        });
    }
    /*
    private class MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {

            DBHelper dbHelper1 = new DBHelper(Memo_mianActivity.this, "LifeHelper_DB", null, 1);
            SQLiteDatabase db = dbHelper1.getReadableDatabase();
            int memo_value = 0;

            Cursor cursor = db.query("memo",null,null,null,null,null,null);
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
            String content,date;
            DBHelper dbHelper1 = new DBHelper(Memo_mianActivity.this, "LifeHelper_DB", null, 1);
            SQLiteDatabase db = dbHelper1.getReadableDatabase();

            Cursor cursor = db.query("memo",null,null,null,null,null,null);
            if (cursor.moveToFirst())
                do {

                    content = cursor.getString(cursor.getColumnIndex("content"));
                    date = cursor.getString(cursor.getColumnIndex("date"));
                }while(cursor.moveToNext());

            TextView textView1 = new TextView(Memo_mianActivity.this);
            textView1.setText(content+i);
            textView1.setTextSize(24);

            return textView1;
        }
    }
    */
    class MyListener_ImageButton_schedule_add implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Schedule_mianActivity.this, Schedule_addActivity.class);
            startActivity(intent); //执行
            finish();
            System.out.println("跳转注册页面执行完毕");
        }
    }


}
