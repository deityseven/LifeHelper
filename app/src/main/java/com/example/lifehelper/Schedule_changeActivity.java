package com.example.lifehelper;

import java.util.Calendar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


public class Schedule_changeActivity extends AppCompatActivity implements OnClickListener {
    private TextView tvShowDialog,
            prompt;
    private Calendar cal;
    private int year,month,day,
            remindDate,
            repeat;

    private Button finish,
            back,
            delete;
    private EditText Schedule_content,
            EditText_memo,
            EditText_place;

    private CheckBox checkBox_remindDate_ontime,
            checkBox_remindDate_Ten,
            checkBox_remindDate_Halfhour,
            checkBox_repeat_Everyday,
            checkBox_repeat_Onceweek,
            checkBox_repeat_month,
            checkBox_repeat_Everyyear,
            checkBox_repeat_no;

    private String phone,
            old_content,
            old_date,
            id,
            memoe_value,
            placee_value;
    private int remindDate_value,
            repeate_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_change);

        //获取当前日期
        getDate();

        finish = (Button)findViewById(R.id.button_Schedule_change_add_finish);
        back = (Button)findViewById(R.id.button_Schedule_newchange_back);
        delete = (Button)findViewById(R.id.button_change_delete);


        Schedule_content = (EditText)findViewById(R.id.Schedule_change_content);
        EditText_memo = (EditText)findViewById(R.id.EditText_change_memo);
        EditText_place = (EditText)findViewById(R.id.EditText_change_place);

        tvShowDialog=(TextView) findViewById(R.id.Schedule_change_date);
        prompt=(TextView) findViewById(R.id.Schedule_change_prompt);

        checkBox_remindDate_ontime = (CheckBox) findViewById(R.id.checkBox_change_remindDate_ontime);
        checkBox_remindDate_Ten = (CheckBox) findViewById(R.id.checkBox_change_remindDate_Ten);
        checkBox_remindDate_Halfhour = (CheckBox) findViewById(R.id.checkBox_change_remindDate_Halfhour);
        checkBox_repeat_Everyday = (CheckBox) findViewById(R.id.checkBox_change_repeat_Everyday);
        checkBox_repeat_Onceweek = (CheckBox) findViewById(R.id.checkBox_change_repeat_Onceweek);
        checkBox_repeat_month = (CheckBox) findViewById(R.id.checkBox_change_repeat_month);
        checkBox_repeat_Everyyear = (CheckBox) findViewById(R.id.checkBox_change_repeat_Everyyear);
        checkBox_repeat_no = (CheckBox) findViewById(R.id.checkBox_change_repeat_no);

        checkBox_remindDate_ontime.setOnClickListener(this);
        checkBox_remindDate_Ten.setOnClickListener(this);
        checkBox_remindDate_Halfhour.setOnClickListener(this);
        checkBox_repeat_Everyday.setOnClickListener(this);
        checkBox_repeat_Onceweek.setOnClickListener(this);
        checkBox_repeat_month.setOnClickListener(this);
        checkBox_repeat_Everyyear.setOnClickListener(this);
        checkBox_repeat_no.setOnClickListener(this);

        tvShowDialog.setOnClickListener(this);
        finish.setOnClickListener(new MyListener_finish());
        back.setOnClickListener(this);
        delete.setOnClickListener(this);

        Intent intent = getIntent();
        phone= intent.getStringExtra("phone");
        old_content= intent.getStringExtra("old_content");
        old_date= intent.getStringExtra("old_date");
        id= intent.getStringExtra("id");

        remindDate_value= intent.getIntExtra("remindDate",0);
        repeate_value= intent.getIntExtra("repeat",0);
        memoe_value= intent.getStringExtra("memo");
        placee_value= intent.getStringExtra("place");

        Schedule_content.setText(old_content);
        tvShowDialog.setText(old_date);
        switch (remindDate_value){
            case 1:
                checkBox_remindDate_ontime.setChecked(true);
                break;
            case 2:
                checkBox_remindDate_Ten.setChecked(true);
                break;
            case 3:
                checkBox_remindDate_Halfhour.setChecked(true);
                break;
            default:
                break;
        }
        switch (repeate_value){
            case 1:
                checkBox_repeat_Everyday.setChecked(true);
                break;
            case 2:
                checkBox_repeat_Onceweek.setChecked(true);
                break;
            case 3:
                checkBox_repeat_month.setChecked(true);
                break;
            case 4:
                checkBox_repeat_Everyyear.setChecked(true);
                break;
            case 5:
                checkBox_repeat_no.setChecked(true);
                break;
            default:
                break;
        }
        EditText_memo.setText(memoe_value);
        EditText_place.setText(placee_value);


    }
    class MyListener_finish implements View.OnClickListener{
        private String phone_value,content_value,date_value,memo_value,place_value;
        @Override
        public void onClick(View v) {

            try{

                DBHelper dbHelper = new DBHelper(Schedule_changeActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();

                Cursor cursor_get_phone = db.query("last_login",null,null,null,null,null,null);
                cursor_get_phone.moveToFirst();
                phone_value = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));
                content_value = Schedule_content.getText().toString();
                date_value = tvShowDialog.getText().toString();

                if (checkBox_remindDate_ontime.isChecked()){
                    remindDate = 1;
                }
                if (checkBox_remindDate_Ten.isChecked()){
                    remindDate = 2;
                }
                if (checkBox_remindDate_Halfhour.isChecked()){
                    remindDate = 3;
                }

                if (checkBox_repeat_Everyday.isChecked()){
                    repeat = 1;
                }
                if (checkBox_repeat_Onceweek.isChecked()){
                    repeat = 2;
                }
                if (checkBox_repeat_month.isChecked()){
                    repeat = 3;
                }
                if (checkBox_repeat_Everyyear.isChecked()){
                    repeat = 4;
                }
                if (checkBox_repeat_no.isChecked()){
                    repeat = 5;
                }
                memo_value = EditText_memo.getText().toString();
                place_value = EditText_place.getText().toString();

                if ( !content_value.equals("") ){
                    if (!date_value.equals("")){
                        if (!(remindDate==0)){
                            if (!(repeat==0)){
                                ContentValues values = new ContentValues();
                                values.put("phone", phone_value);
                                values.put("content", content_value);
                                values.put("date", date_value);
                                values.put("remindDate", remindDate);
                                values.put("repeat", repeat);
                                values.put("memo", memo_value);
                                values.put("place", place_value);

                                db1.update("schedule", values, "id="+id ,null);
                                System.out.println("schedule表插入成功:");

                                Intent intent = new Intent(Schedule_changeActivity.this, Schedule_mianActivity.class);
                                startActivity(intent); //执行
                                System.out.println("跳转日程主页执行完毕");
                                finish();
                            }
                        }
                        prompt.setText("提醒和重复未勾选或请点击返回");
                    }else {
                        prompt.setText("日期为空，输入内容或请点击返回");
                        System.out.println("日期为空，错误操作");
                    }
                }else{
                    prompt.setText("内容为空，输入内容或请点击返回");
                    System.out.println("内容为空，错误操作");
                }

            }catch (Exception e){
                System.out.println("修改数据异常出错");
                Intent intent = new Intent(Schedule_changeActivity.this, Schedule_mianActivity.class);
                startActivity(intent); //执行
                System.out.println("出错跳转Schedule_mian执行完毕");
                finish();
            }



        }
    }
    //获取当前日期
    private void getDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onClick(View view) {
        System.out.println("checkbox被点击"+view.getId()+(view.getId()==R.id.Schedule_change_date));
        switch (view.getId()) {
            case R.id.Schedule_change_date:
                OnDateSetListener listener=new OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        tvShowDialog.setText(year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(Schedule_changeActivity.this, DatePickerDialog.THEME_HOLO_LIGHT,listener,year,month,day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;
            case R.id.checkBox_change_remindDate_Halfhour:
                checkBox_remindDate_ontime.setChecked(false);
                checkBox_remindDate_Ten.setChecked(false);
                break;
            case R.id.checkBox_change_remindDate_ontime:
                checkBox_remindDate_Halfhour.setChecked(false);
                checkBox_remindDate_Ten.setChecked(false);
                break;
            case R.id.checkBox_change_remindDate_Ten:
                checkBox_remindDate_ontime.setChecked(false);
                checkBox_remindDate_Halfhour.setChecked(false);
                break;
            case R.id.checkBox_change_repeat_Everyday:
                checkBox_repeat_Everyyear.setChecked(false);
                checkBox_repeat_month.setChecked(false);
                checkBox_repeat_no.setChecked(false);
                checkBox_repeat_Onceweek.setChecked(false);
                break;
            case R.id.checkBox_change_repeat_Everyyear:
                checkBox_repeat_Everyday.setChecked(false);
                checkBox_repeat_month.setChecked(false);
                checkBox_repeat_no.setChecked(false);
                checkBox_repeat_Onceweek.setChecked(false);
                break;
            case R.id.checkBox_change_repeat_month:
                checkBox_repeat_Everyyear.setChecked(false);
                checkBox_repeat_Everyday.setChecked(false);
                checkBox_repeat_no.setChecked(false);
                checkBox_repeat_Onceweek.setChecked(false);
                break;
            case R.id.checkBox_change_repeat_no:
                checkBox_repeat_Everyyear.setChecked(false);
                checkBox_repeat_month.setChecked(false);
                checkBox_repeat_Everyday.setChecked(false);
                checkBox_repeat_Onceweek.setChecked(false);
                break;
            case R.id.checkBox_change_repeat_Onceweek:
                checkBox_repeat_Everyyear.setChecked(false);
                checkBox_repeat_month.setChecked(false);
                checkBox_repeat_no.setChecked(false);
                checkBox_repeat_Everyday.setChecked(false);
                break;
            case R.id.button_Schedule_newchange_back:
                Intent intent = new Intent(Schedule_changeActivity.this, Schedule_mianActivity.class);
                startActivity(intent); //执行
                System.out.println("back跳转Schedule_mian执行完毕");
                finish();
                break;
            case R.id.button_change_delete:
                DBHelper dbHelper = new DBHelper(Schedule_changeActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                db.delete("schedule","id="+id,null);
                db.close();

                Intent intent_delete = new Intent(Schedule_changeActivity.this, Schedule_mianActivity.class);
                startActivity(intent_delete); //执行
                System.out.println("back跳转Schedule_mian执行完毕");
                finish();
                break;

            default:
                break;
        }

    }
}