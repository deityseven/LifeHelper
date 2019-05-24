package com.example.lifehelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TouxiangSetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button
            touxiang_button1,
            touxiang_button2,
            touxiang_button3,
            touxiang_button4,
            touxiang_button5,
            touxiang_button6,
            touxiang_button7,
            touxiang_button8,
            touxiang_button9,
            touxiang_button10,
            touxiang_button11,
            touxiang_button12,
            touxiang_button13,
            touxiang_button14,
            touxiang_button15,
            touxiang_button16;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touxiang_set);

        SetGetID();
        SetOnclick();
        Setcolor();


    }

    private void SetGetID() {
        touxiang_button1 = (Button)findViewById(R.id.touxiang_button1);
        touxiang_button2 = (Button)findViewById(R.id.touxiang_button2);
        touxiang_button3 = (Button)findViewById(R.id.touxiang_button3);
        touxiang_button4 = (Button)findViewById(R.id.touxiang_button4);
        touxiang_button5 = (Button)findViewById(R.id.touxiang_button5);
        touxiang_button6 = (Button)findViewById(R.id.touxiang_button6);
        touxiang_button7 = (Button)findViewById(R.id.touxiang_button7);
        touxiang_button8 = (Button)findViewById(R.id.touxiang_button8);
        touxiang_button9 = (Button)findViewById(R.id.touxiang_button9);
        touxiang_button10 = (Button)findViewById(R.id.touxiang_button10);
        touxiang_button11 = (Button)findViewById(R.id.touxiang_button11);
        touxiang_button12 = (Button)findViewById(R.id.touxiang_button12);
        touxiang_button13 = (Button)findViewById(R.id.touxiang_button13);
        touxiang_button14 = (Button)findViewById(R.id.touxiang_button14);
        touxiang_button15 = (Button)findViewById(R.id.touxiang_button15);
        touxiang_button16 = (Button)findViewById(R.id.touxiang_button16);
    }

    private void SetOnclick() {
        touxiang_button1.setOnClickListener(this);
        touxiang_button2.setOnClickListener(this);
        touxiang_button3.setOnClickListener(this);
        touxiang_button4.setOnClickListener(this);
        touxiang_button5.setOnClickListener(this);
        touxiang_button6.setOnClickListener(this);
        touxiang_button7.setOnClickListener(this);
        touxiang_button8.setOnClickListener(this);
        touxiang_button9.setOnClickListener(this);
        touxiang_button10.setOnClickListener(this);
        touxiang_button11.setOnClickListener(this);
        touxiang_button12.setOnClickListener(this);
        touxiang_button13.setOnClickListener(this);
        touxiang_button14.setOnClickListener(this);
        touxiang_button15.setOnClickListener(this);
        touxiang_button16.setOnClickListener(this);
    }

    private void Setcolor() {

        touxiang_button1.setBackgroundResource(R.drawable.tx1);
        touxiang_button2.setBackgroundResource(R.drawable.tx2);
        touxiang_button3.setBackgroundResource(R.drawable.tx3);
        touxiang_button4.setBackgroundResource(R.drawable.tx4);
        touxiang_button5.setBackgroundResource(R.drawable.tx5);
        touxiang_button6.setBackgroundResource(R.drawable.tx6);
        touxiang_button7.setBackgroundResource(R.drawable.tx7);
        touxiang_button8.setBackgroundResource(R.drawable.tx8);
        touxiang_button9.setBackgroundResource(R.drawable.tx9);
        touxiang_button10.setBackgroundResource(R.drawable.tx10);
        touxiang_button11.setBackgroundResource(R.drawable.tx11);
        touxiang_button12.setBackgroundResource(R.drawable.tx12);
        touxiang_button13.setBackgroundResource(R.drawable.tx13);
        touxiang_button14.setBackgroundResource(R.drawable.tx14);
        touxiang_button15.setBackgroundResource(R.drawable.tx15);
        touxiang_button16.setBackgroundResource(R.drawable.tx16);
    }


    @Override
    public void onClick(View view) {
        WriteColorToDatebase(view.getId());
        GoSetFinish();
    }

    private void WriteColorToDatebase(int id) {
        Button buttonx = (Button)findViewById(id);
        buttonx.getBackground();
        DBHelper dbHelper1 = new DBHelper(TouxiangSetActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db1 = dbHelper1.getReadableDatabase();
        Cursor cursor_get_phone = db1.query("last_login",null,null,null,null,null,null);
        cursor_get_phone.moveToFirst();
        String phone = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));

        ContentValues values = new ContentValues();
        values.put("tx",  ColorSelecter(id));
        db1.update("user_information", values, "phone="+phone ,null);
        db1.close();

    }

    private int ColorSelecter(int id) {
        int touxiang = 0;
        switch (id){
            case R.id.touxiang_button1:
                touxiang = 1;
                break;
            case R.id.touxiang_button2:
                touxiang = 2;
                break;
            case R.id.touxiang_button3:
                touxiang = 3;
                break;
            case R.id.touxiang_button4:
                touxiang = 4;
                break;
            case R.id.touxiang_button5:
                touxiang = 5;
                break;
            case R.id.touxiang_button6:
                touxiang = 6;
                break;
            case R.id.touxiang_button7:
                touxiang = 7;
                break;
            case R.id.touxiang_button8:
                touxiang = 8;
                break;
            case R.id.touxiang_button9:
                touxiang = 9;
                break;
            case R.id.touxiang_button10:
                touxiang = 10;
                break;
            case R.id.touxiang_button11:
                touxiang = 11;
                break;
            case R.id.touxiang_button12:
                touxiang = 12;
                break;
            case R.id.touxiang_button13:
                touxiang = 13;
                break;
            case R.id.touxiang_button14:
                touxiang = 14;
                break;
            case R.id.touxiang_button15:
                touxiang = 15;
                break;
            case R.id.touxiang_button16:
                touxiang = 16;
                break;

            default:
                break;
        }
        return touxiang;
    }

    private void GoSetFinish() {
        Intent intent = new Intent(TouxiangSetActivity.this,UerChangeActivity.class);
        startActivity(intent);
        finish();
    }
}