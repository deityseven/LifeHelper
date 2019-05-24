package com.example.lifehelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ColorSetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button
            color_button1,
            color_button2,
            color_button3,
            color_button4,
            color_button5,
            color_button6,
            color_button7,
            color_button8,
            color_button9,
            color_button10,
            color_button11,
            color_button12,
            color_button13,
            color_button14,
            color_button15,
            color_button16;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_set);

        SetGetID();
        SetOnclick();
        Setcolor();


    }

    private void SetGetID() {
        color_button1 = (Button)findViewById(R.id.color_button1);
        color_button2 = (Button)findViewById(R.id.color_button2);
        color_button3 = (Button)findViewById(R.id.color_button3);
        color_button4 = (Button)findViewById(R.id.color_button4);
        color_button5 = (Button)findViewById(R.id.color_button5);
        color_button6 = (Button)findViewById(R.id.color_button6);
        color_button7 = (Button)findViewById(R.id.color_button7);
        color_button8 = (Button)findViewById(R.id.color_button8);
        color_button9 = (Button)findViewById(R.id.color_button9);
        color_button10 = (Button)findViewById(R.id.color_button10);
        color_button11 = (Button)findViewById(R.id.color_button11);
        color_button12 = (Button)findViewById(R.id.color_button12);
        color_button13 = (Button)findViewById(R.id.color_button13);
        color_button14 = (Button)findViewById(R.id.color_button14);
        color_button15 = (Button)findViewById(R.id.color_button15);
        color_button16 = (Button)findViewById(R.id.color_button16);
    }

    private void SetOnclick() {
        color_button1.setOnClickListener(this);
        color_button2.setOnClickListener(this);
        color_button3.setOnClickListener(this);
        color_button4.setOnClickListener(this);
        color_button5.setOnClickListener(this);
        color_button6.setOnClickListener(this);
        color_button7.setOnClickListener(this);
        color_button8.setOnClickListener(this);
        color_button9.setOnClickListener(this);
        color_button10.setOnClickListener(this);
        color_button11.setOnClickListener(this);
        color_button12.setOnClickListener(this);
        color_button13.setOnClickListener(this);
        color_button14.setOnClickListener(this);
        color_button15.setOnClickListener(this);
        color_button16.setOnClickListener(this);
    }

    private void Setcolor() {

        color_button1.setBackgroundColor(getResources().getColor(R.color.green));
        color_button2.setBackgroundColor(getResources().getColor(R.color.yellow));
        color_button3.setBackgroundColor(getResources().getColor(R.color.mistyrose));
        color_button4.setBackgroundColor(getResources().getColor(R.color.darkorange));
        color_button5.setBackgroundColor(getResources().getColor(R.color.magenta));
        color_button6.setBackgroundColor(getResources().getColor(R.color.linen));
        color_button7.setBackgroundColor(getResources().getColor(R.color.salmon));
        color_button8.setBackgroundColor(getResources().getColor(R.color.beige));
        color_button9.setBackgroundColor(getResources().getColor(R.color.lightsteelblue));
        color_button10.setBackgroundColor(getResources().getColor(R.color.darkgray));
        color_button11.setBackgroundColor(getResources().getColor(R.color.blueviolet));
        color_button12.setBackgroundColor(getResources().getColor(R.color.purple));
        color_button13.setBackgroundColor(getResources().getColor(R.color.slategrey));
        color_button14.setBackgroundColor(getResources().getColor(R.color.darkslateblue));
        color_button15.setBackgroundColor(getResources().getColor(R.color.springgreen));
        color_button16.setBackgroundColor(getResources().getColor(R.color.mediumblue));
    }


    @Override
    public void onClick(View view) {
        WriteColorToDatebase(view.getId());
        GoSetFinish();
    }

    private void WriteColorToDatebase(int id) {
        Button buttonx = (Button)findViewById(id);
        buttonx.getBackground();
        DBHelper dbHelper1 = new DBHelper(ColorSetActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db1 = dbHelper1.getReadableDatabase();
        Cursor cursor_get_phone = db1.query("last_login",null,null,null,null,null,null);
        cursor_get_phone.moveToFirst();
        String phone = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));

        ContentValues values = new ContentValues();
        values.put("color",  ColorSelecter(id));
        db1.update("user_information", values, "phone="+phone ,null);
        db1.close();

    }

    private int ColorSelecter(int id) {
        int color = 0;
        switch (id){
            case R.id.color_button1:
                color = Color.parseColor("#008000");
                break;
            case R.id.color_button2:
                color = Color.parseColor("#FFFF00");
                break;
            case R.id.color_button3:
                color = Color.parseColor("#FFE4E1");
                break;
            case R.id.color_button4:
                color = Color.parseColor("#FF8C00");
                break;
            case R.id.color_button5:
                color = Color.parseColor("#FF00FF");
                break;
            case R.id.color_button6:
                color = Color.parseColor("#FAF0E6");
                break;
            case R.id.color_button7:
                color = Color.parseColor("#FA8072");
                break;
            case R.id.color_button8:
                color = Color.parseColor("#F5F5DC");
                break;
            case R.id.color_button9:
                color = Color.parseColor("#B0C4DE");
                break;
            case R.id.color_button10:
                color = Color.parseColor("#A9A9A9");
                break;
            case R.id.color_button11:
                color = Color.parseColor("#8A2BE2");
                break;
            case R.id.color_button12:
                color = Color.parseColor("#800080");
                break;
            case R.id.color_button13:
                color = Color.parseColor("#708090");
                break;
            case R.id.color_button14:
                color = Color.parseColor("#483D8B");
                break;
            case R.id.color_button15:
                color = Color.parseColor("#00FF7F");
                break;
            case R.id.color_button16:
                color = Color.parseColor("#0000CD");
                break;

            default:
                break;
        }
        return color;
    }

    private void GoSetFinish() {
        Intent intent = new Intent(ColorSetActivity.this,UerChangeActivity.class);
        startActivity(intent);
        finish();
    }
}

