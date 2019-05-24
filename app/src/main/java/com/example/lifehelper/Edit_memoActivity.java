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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


public class Edit_memoActivity extends AppCompatActivity implements OnClickListener {
    private TextView tvShowDialog,prompt;
    private Calendar cal;
    private int year,month,day;

    private Button finish;
    private EditText editText_memo_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memo);

        //获取当前日期
        getDate();

        finish = (Button)findViewById(R.id.button_news_add_finish);
        editText_memo_content = (EditText)findViewById(R.id.editText_memo_content);
        tvShowDialog=(TextView) findViewById(R.id.textView3);
        prompt=(TextView) findViewById(R.id.textView4);

        tvShowDialog.setOnClickListener(this);
        finish.setOnClickListener(new MyListener_finish());
    }
    class MyListener_finish implements View.OnClickListener{
        private String phone_value,content_value,date_value;
        @Override
        public void onClick(View v) {

            try{

                DBHelper dbHelper = new DBHelper(Edit_memoActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();

                Cursor cursor_get_phone = db.query("last_login",null,null,null,null,null,null);
                cursor_get_phone.moveToFirst();
                phone_value = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));
                content_value = editText_memo_content.getText().toString();
                date_value = tvShowDialog.getText().toString();

                if ( !content_value.equals("") ){
                    if (!date_value.equals("")){
                        ContentValues values = new ContentValues();
                        values.put("phone", phone_value);
                        values.put("content", content_value);
                        values.put("date", date_value);

                        db1.insert("memo", null, values);
                        System.out.println("memo表插入成功:");

                        Intent intent = new Intent(Edit_memoActivity.this, Memo_mianActivity.class);
                        startActivity(intent); //执行
                        System.out.println("跳转备忘录主页执行完毕");
                        finish();
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
                Intent intent = new Intent(Edit_memoActivity.this, Memo_mianActivity.class);
                startActivity(intent); //执行
                System.out.println("出错跳转memo_mian执行完毕");
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
        switch (view.getId()) {
            case R.id.textView3:
                OnDateSetListener listener=new OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        tvShowDialog.setText(year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(Edit_memoActivity.this, DatePickerDialog.THEME_HOLO_LIGHT,listener,year,month,day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;

            default:
                break;
        }

    }
}