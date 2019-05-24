package com.example.lifehelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UerChangeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView touxiang;
    private LinearLayout BGcolor,BG1,BG2,BG3;
    private TextView TextView_phone_num_change,
            TextView_memo_num_change,
            TextView_schedule_num_change,
            TextView_news_num_change,BG4;
    private Button button_change_save;
    private EditText
            EditText_name_num,
            EditText_sex_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uer_change);

        FindViewById();
        SetOnClickListener();

        setImgecolor();
        setHint();
    }
    private void SetOnClickListener() {
        touxiang.setOnClickListener(this);
        button_change_save.setOnClickListener(this);
        BG1.setOnClickListener(this);
        BG2.setOnClickListener(this);
        BG3.setOnClickListener(this);
        BG4.setOnClickListener(this);
    }
    private void FindViewById() {
        touxiang = (ImageView)findViewById(R.id.imageView_change_touxiang);
        EditText_name_num = (EditText) findViewById(R.id.EditText_name_num);
        TextView_phone_num_change = (TextView) findViewById(R.id.TextView_phone_num_change);
        EditText_sex_num = (EditText) findViewById(R.id.EditText_sex_num);
        TextView_memo_num_change = (TextView) findViewById(R.id.TextView_memo_num_change);
        TextView_schedule_num_change = (TextView) findViewById(R.id.TextView_schedule_num_change);
        TextView_news_num_change = (TextView) findViewById(R.id.TextView_news_num_change);
        BGcolor = (LinearLayout)findViewById(R.id.laout_BGcolor);
        BG1 = (LinearLayout)findViewById(R.id.BG1);
        BG2 = (LinearLayout)findViewById(R.id.BG2);
        BG3 = (LinearLayout)findViewById(R.id.BG3);
        BG4 = (TextView)findViewById(R.id.BG4);
        button_change_save = (Button)findViewById(R.id.button_change_save);
    }
    private void setImgecolor() {
        DBHelper dbHelper1 = new DBHelper(UerChangeActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db1 = dbHelper1.getReadableDatabase();
        Cursor cursor_get_phone = db1.query("last_login",null,null,null,null,null,null);
        cursor_get_phone.moveToFirst();
        String phone = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));

        String[] columns = new  String[] {"phone","color","tx"};
        Cursor cursor_get_user_information = db1.query("user_information",columns,"phone="+phone,null,null,null,null);
        cursor_get_user_information.moveToFirst();
        int color_value = cursor_get_user_information.getInt(cursor_get_user_information.getColumnIndex("color"));
        int tx_value = cursor_get_user_information.getInt(cursor_get_user_information.getColumnIndex("tx"));
        ColorDrawable drawable = new ColorDrawable(color_value);
        BGcolor.setBackgroundDrawable(drawable);
        TXset(tx_value);
    }

    private void TXset(int tx_id) {
        switch (tx_id){
            case 1:
                touxiang.setBackgroundResource(R.drawable.tx1);
                break;
            case 2:
                touxiang.setBackgroundResource(R.drawable.tx2);
                break;
            case 3:
                touxiang.setBackgroundResource(R.drawable.tx3);
                break;
            case 4:
                touxiang.setBackgroundResource(R.drawable.tx4);
                break;
            case 5:
                touxiang.setBackgroundResource(R.drawable.tx5);
                break;
            case 6:
                touxiang.setBackgroundResource(R.drawable.tx6);
                break;
            case 7:
                touxiang.setBackgroundResource(R.drawable.tx7);
                break;
            case 8:
                touxiang.setBackgroundResource(R.drawable.tx8);
                break;
            case 9:
                touxiang.setBackgroundResource(R.drawable.tx9);
                break;
            case 10:
                touxiang.setBackgroundResource(R.drawable.tx10);
                break;
            case 11:
                touxiang.setBackgroundResource(R.drawable.tx11);
                break;
            case 12:
                touxiang.setBackgroundResource(R.drawable.tx12);
                break;
            case 13:
                touxiang.setBackgroundResource(R.drawable.tx13);
                break;
            case 14:
                touxiang.setBackgroundResource(R.drawable.tx14);
                break;
            case 15:
                touxiang.setBackgroundResource(R.drawable.tx15);
                break;
            case 16:
                touxiang.setBackgroundResource(R.drawable.tx16);
                break;
            default:
                break;
        }
    }

    private void setHint() {
        DBHelper dbHelper1 = new DBHelper(UerChangeActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db1 = dbHelper1.getReadableDatabase();
        Cursor cursor_get_phone = db1.query("last_login",null,null,null,null,null,null);
        cursor_get_phone.moveToFirst();
        String phone = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));

        String[] columns = new  String[] {"phone","name","sex","number_of_memo","number_of_schedule","number_of_news"};
        Cursor cursor_get_user_information = db1.query("user_information",columns,"phone="+phone,null,null,null,null);
        cursor_get_user_information.moveToFirst();

        EditText_name_num.setText((cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("name"))));
        TextView_phone_num_change.setText((cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("phone"))));
        EditText_sex_num.setText((cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("sex"))));
        TextView_memo_num_change.setText((cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("number_of_memo"))));
        TextView_schedule_num_change.setText((cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("number_of_schedule"))));
        TextView_news_num_change.setText((cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("number_of_news"))));
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView_change_touxiang:
                Intent intent3 = new Intent(UerChangeActivity.this,TouxiangSetActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.button_change_save:
                SaveChange();
                Intent intent1 = new Intent(UerChangeActivity.this,User_mainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.BG1:
            case R.id.BG2:
            case R.id.BG3:
            case R.id.BG4:
                Intent intent = new Intent(UerChangeActivity.this,ColorSetActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void SaveChange() {
        DBHelper dbHelper1 = new DBHelper(UerChangeActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db1 = dbHelper1.getWritableDatabase();
        Cursor cursor_get_phone = db1.query("last_login",null,null,null,null,null,null);
        cursor_get_phone.moveToFirst();
        String phone = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));
        ContentValues values = new ContentValues();
        values.put("name",EditText_name_num.getText().toString());
        values.put("sex",EditText_sex_num.getText().toString());
        db1.update("user_information", values, "phone="+phone ,null);
        db1.close();
    }
}
