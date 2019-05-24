package com.example.lifehelper;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View.OnClickListener;

public class Memo_editActivity extends AppCompatActivity implements OnClickListener{
    private TextView memo_old_content,
            memo_old_date,
            memo_new_date,
            prompt;
    private EditText new_memo_content;
    private Button change_finish,
            change_back,
            empty_content,
            empty_date,
            delete_memo;


    private Calendar cal;
    private int year,month,day;
    private String phone,old_content,old_date,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_edit);

        memo_old_content = (TextView) findViewById(R.id.TextView_memo_old_content);
        memo_old_date = (TextView) findViewById(R.id.TextView_memo_old_date);
        memo_new_date = (TextView) findViewById(R.id.TextView_memo_new_date);
        prompt = (TextView) findViewById(R.id.TextView_prompt);
        new_memo_content = (EditText) findViewById(R.id.editText_new_memo_content);
        change_finish = (Button) findViewById(R.id.button_change_finish);
        change_back = (Button) findViewById(R.id.button_change_back);
        empty_content = (Button) findViewById(R.id.button_empty_content);
        empty_date = (Button) findViewById(R.id.button_empty_date);
        delete_memo = (Button) findViewById(R.id.button_delete_memo);

        getDate();

        Intent intent = getIntent();
        phone= intent.getStringExtra("phone");
        old_content= intent.getStringExtra("old_content");
        old_date= intent.getStringExtra("old_date");
        id= intent.getStringExtra("id");

        memo_old_content.setText(old_content);
        memo_old_date.setText(old_date);

        memo_new_date.setOnClickListener(this);
        change_finish.setOnClickListener(this);
        change_back.setOnClickListener(this);
        empty_content.setOnClickListener(this);
        empty_date.setOnClickListener(this);
        delete_memo.setOnClickListener(this);

        new_memo_content.setText(old_content);
        memo_new_date.setText(old_date);

    }

    private void getDate() {
        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);       //获取年月日时分秒
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TextView_memo_new_date:
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        memo_new_date.setText(year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(Memo_editActivity.this, DatePickerDialog.THEME_HOLO_LIGHT,listener,year,month,day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;
            case R.id.button_change_finish:
                try{
                    DBHelper dbHelper1 = new DBHelper(Memo_editActivity.this, "LifeHelper_DB", null, 1);
                    SQLiteDatabase db = dbHelper1.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    System.out.println(new_memo_content.getText().toString().equals(""));
                    System.out.println(memo_new_date.getText().toString().equals(""));
                    if ( !new_memo_content.getText().toString().equals("") ){
                        if (!memo_new_date.getText().toString().equals("")){
                            values.put("phone", phone);
                            values.put("content", new_memo_content.getText().toString());
                            values.put("date", memo_new_date.getText().toString());
                            db.update("memo", values, "id="+id ,null);
                            db.close();
                            System.out.println("数据库更行执行完毕");

                            Intent intent = new Intent(Memo_editActivity.this, Memo_mianActivity.class);
                            startActivity(intent); //执行
                            System.out.println("跳转memo_mian执行完毕");
                            finish();
                        }else {
                            prompt.setText("日期为空，输入内容或请点击返回键或删除记录");
                            System.out.println("日期为空，错误操作");
                        }
                    }else{
                        prompt.setText("内容为空，输入内容或请点击返回键或删除记录");
                        System.out.println("内容为空，错误操作");
                    }

                }catch (Exception e){
                    System.out.println("修改数据异常出错");
                    Intent intent = new Intent(Memo_editActivity.this, Memo_mianActivity.class);
                    startActivity(intent); //执行
                    System.out.println("出错跳转memo_mian执行完毕");
                    finish();
                }



                break;
            case R.id.button_change_back:
                Intent intent = new Intent(Memo_editActivity.this, Memo_mianActivity.class);
                startActivity(intent); //执行
                System.out.println("BACK跳转memo_mian执行完毕");
                finish();

                break;
            case R.id.button_empty_content:
                new_memo_content.setText("");
                break;
            case R.id.button_empty_date:
                memo_new_date.setText("");
                break;
            case R.id.button_delete_memo:
                DBHelper dbHelper1 = new DBHelper(Memo_editActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db = dbHelper1.getWritableDatabase();
                db.delete("memo", "id="+id, null);
                Intent intent_delete = new Intent(Memo_editActivity.this, Memo_mianActivity.class);
                startActivity(intent_delete); //执行
                System.out.println("DELETE跳转memo_mian执行完毕");
                db.close();
                finish();
                break;

            default:
                break;
        }

    }
}
