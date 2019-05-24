package com.example.lifehelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class RegisteredActivity extends AppCompatActivity {
    private EditText phone;
    private EditText password;
    private CheckBox nan;
    private CheckBox nv;
    private Button Register;
    private TextView textView_ppp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        phone = (EditText)findViewById(R.id.editText_phone);
        password = (EditText)findViewById(R.id.editText_password);
        nan = (CheckBox)findViewById(R.id.checkBox_nan);
        nv = (CheckBox)findViewById(R.id.checkBox_nv);
        Register = (Button)findViewById(R.id.button_login);
        textView_ppp = (TextView)findViewById(R.id.textView_ppp);
        phone.setHint(R.string.phone);
        password.setHint(R.string.password);

        nan.setOnClickListener(new MyListener_nan());
        nv.setOnClickListener(new MyListener_nv());
        Register.setOnClickListener(new MyListener_Register());
        phone.setOnClickListener(new phoneliset());
        password.setOnClickListener(new phoneliset());
    }
    class phoneliset implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            textView_ppp.setText("");
        }
    }
    class MyListener_nan implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            nv.setChecked(false);
            textView_ppp.setText("");
        }
    }
    class MyListener_nv implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            nan.setChecked(false);
            textView_ppp.setText("");
        }
    }
    class MyListener_Register implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            textView_ppp.setText("");
            String pho,pas,se;
            pho = phone.getText().toString();
            pas = password.getText().toString();
            if (nan.isChecked())
            {
                se = "男";
            }
            else {
                se = "女";
            }
            if (pho.equals("")||pas.equals("")||se.equals("")){
                textView_ppp.setText("注册信息\n任意一项\n不能为空");
            }else{
                DBHelper dbHelper1 = new DBHelper(RegisteredActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db1 = dbHelper1.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("phone", pho);
                values.put("password", pas);
                values.put("sex", se);

                db1.insert("user", null, values);

                ContentValues values1 = new ContentValues();
                values1.put("phone", pho);
                values1.put("name", "");
                values1.put("sex", se);
                values1.put("number_of_memo", 0);
                values1.put("number_of_schedule", 0);
                values1.put("number_of_news",0);

                db1.insert("user_information", null, values1);
                db1.close();
                System.out.println("user_information表写入成功");


                Intent intent = new Intent(RegisteredActivity.this, LoginActivity.class);
                startActivity(intent); //执行
                finish();
            }


            /*
            try{

                ContentValues cValue = new ContentValues();
                cValue.put("phone",pho);
                cValue.put("password",pas);
                cValue.put("sex",se);
                db.insert("user",null,cValue);
                Toast.makeText(RegisteredActivity.this,"注册成功,正在前往登录。",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(RegisteredActivity.this, LoginActivity.class);
                startActivity(it); //执行
                finish();
            }catch(Exception e){
                Toast.makeText(RegisteredActivity.this,"跳转失败,请重试。",Toast.LENGTH_SHORT).show();
            }
            */

        }

    }
}
