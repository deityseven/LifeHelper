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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class User_mainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button change,
            exitlogin,
            button_usermain_back;
    private LinearLayout laout_BGcolor;
    private TextView name_num,
            phone_num,
            sex_num,
            memo_num,
            schedule_num,
            news_num,
            TextView_name_intent;
    private ImageView imageView_change_touxiang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        FindViewById();
        SetOnClickListener();
        get_user_information();

    }

    private void SetOnClickListener() {
        change.setOnClickListener(this);
        exitlogin.setOnClickListener(this);
        button_usermain_back.setOnClickListener(this);
    }

    private void FindViewById() {
        change = (Button)findViewById(R.id.button_change);
        button_usermain_back = (Button)findViewById(R.id.button_usermain_back);
        exitlogin = (Button)findViewById(R.id.button_exit);
        laout_BGcolor = (LinearLayout)findViewById(R.id.laout_BGcolor);
        name_num = (TextView)findViewById(R.id.TextView_name_num) ;
        phone_num = (TextView)findViewById(R.id.TextView_phone_num) ;
        sex_num = (TextView)findViewById(R.id.TextView_sex_num) ;
        memo_num = (TextView)findViewById(R.id.TextView_memo_num) ;
        schedule_num = (TextView)findViewById(R.id.TextView_schedule_num) ;
        news_num = (TextView)findViewById(R.id.TextView_news_num) ;
        TextView_name_intent = (TextView)findViewById(R.id.TextView_name_intent);
        imageView_change_touxiang = (ImageView)findViewById(R.id.imageView_change_touxiang);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_change:
                Intent intent = new Intent(User_mainActivity.this,UerChangeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button_exit:
                changedate();
                Intent intent1 = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                finish();
                break;
            case R.id.button_usermain_back:
                Intent intent3 = new Intent(User_mainActivity.this,MainActivity.class);
                startActivity(intent3);
                finish();
                break;
            default:
                break;
        }
    }

    private void changedate() {
        DBHelper dbHelper1 = new DBHelper(User_mainActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db1 = dbHelper1.getReadableDatabase();
        SQLiteDatabase db = dbHelper1.getWritableDatabase();

        Cursor cursor_get_phone = db1.query("last_login",null,null,null,null,null,null);
        cursor_get_phone.moveToFirst();
        String phone = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));


        ContentValues values = new ContentValues();
        values.put("phone", phone);
        values.put("password", "");
        values.put("record", "0");
        db.update("record_password", values, "phone="+phone ,null);
        db.close();
        db1.close();
    }
    public void get_user_information(){
        String phone;
        contet();
        DBHelper dbHelper1 = new DBHelper(User_mainActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db1 = dbHelper1.getReadableDatabase();

        Cursor cursor_get_phone = db1.query("last_login",null,null,null,null,null,null);
        cursor_get_phone.moveToFirst();
        phone = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));

        System.out.println(phone);

        String[] columns = new  String[] {"phone","name","sex","tx","color","number_of_memo","number_of_schedule","number_of_news"};
        Cursor cursor_get_user_information = db1.query("user_information",columns,"phone="+phone,null,null,null,null);
        cursor_get_user_information.moveToFirst();
        System.out.println(cursor_get_user_information.moveToFirst());

        System.out.println(cursor_get_user_information.getColumnIndex("name"));
        name_num.setText(cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("name")));
        phone_num.setText(cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("phone")));
        sex_num.setText(cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("sex")));
        int color_value = cursor_get_user_information.getInt(cursor_get_user_information.getColumnIndex("color"));
        int tx = cursor_get_user_information.getInt(cursor_get_user_information.getColumnIndex("tx"));
        ColorDrawable drawable = new ColorDrawable(color_value);
        laout_BGcolor.setBackgroundDrawable(drawable);
        memo_num.setText(cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("number_of_memo")));
        schedule_num.setText(cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("number_of_schedule")));
        news_num.setText(cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("number_of_news")));
        TextView_name_intent.setText(cursor_get_user_information.getString(cursor_get_user_information.getColumnIndex("name")));
        TXset(tx);
        db1.close();
    }

    private void contet() {
        String phone;
        int number_of_memo_value = 0,number_of_schedule_value = 0,number_of_news_value = 0;

        DBHelper dbHelper1 = new DBHelper(User_mainActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db1 = dbHelper1.getReadableDatabase();

        Cursor cursor_get_phone = db1.query("last_login",null,null,null,null,null,null);
        cursor_get_phone.moveToFirst();
        phone = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));

        //String[] columns = new  String[] {"phone","name","sex","tx","color","number_of_memo","number_of_schedule","number_of_news"};
        Cursor cursor_get_memo_num = db1.query("memo",null,"phone="+phone,null,null,null,null);
        if(cursor_get_memo_num!=null&&cursor_get_memo_num.moveToFirst()){
            do{
                number_of_memo_value = number_of_memo_value + 1;
            }while(cursor_get_memo_num.moveToNext());
        }
        Cursor cursor_get_schedule_num = db1.query("schedule",null,"phone="+phone,null,null,null,null);
        if(cursor_get_schedule_num!=null&&cursor_get_schedule_num.moveToFirst()){
            do{
                number_of_schedule_value = number_of_schedule_value + 1;
            }while(cursor_get_schedule_num.moveToNext());
        }
        Cursor cursor_get_news_num = db1.query("news",null,"phone="+phone,null,null,null,null);
        if(cursor_get_news_num!=null&&cursor_get_news_num.moveToFirst()){
            do{
                number_of_news_value = number_of_news_value + 1;
            }while(cursor_get_news_num.moveToNext());
        }
        ContentValues values = new ContentValues();
        values.put("number_of_memo",number_of_memo_value);
        values.put("number_of_schedule",number_of_schedule_value);
        values.put("number_of_news",number_of_news_value);
        db1.update("user_information", values, "phone="+phone ,null);
        db1.close();
    }

    private void TXset(int tx_id) {
        switch (tx_id){
            case 1:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx1);
                break;
            case 2:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx2);
                break;
            case 3:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx3);
                break;
            case 4:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx4);
                break;
            case 5:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx5);
                break;
            case 6:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx6);
                break;
            case 7:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx7);
                break;
            case 8:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx8);
                break;
            case 9:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx9);
                break;
            case 10:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx10);
                break;
            case 11:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx11);
                break;
            case 12:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx12);
                break;
            case 13:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx13);
                break;
            case 14:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx14);
                break;
            case 15:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx15);
                break;
            case 16:
                imageView_change_touxiang.setBackgroundResource(R.drawable.tx16);
                break;
            default:
                break;
        }
    }
}
