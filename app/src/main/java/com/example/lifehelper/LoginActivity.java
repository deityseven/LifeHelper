package com.example.lifehelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText phone;
    private EditText password;
    private Button login,registered;
    private CheckBox jizhumima;
    private TextView textView_loginppp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = (EditText)findViewById(R.id.editText_phone);
        password = (EditText)findViewById(R.id.editText_password);
        login = (Button)findViewById(R.id.button_login);
        registered = (Button)findViewById(R.id.button_registered);
        jizhumima = (CheckBox) findViewById((R.id.CheckBox_jizhumima));
        textView_loginppp = (TextView)findViewById(R.id.textView_loginppp);

        phone.setHint(R.string.phone);
        password.setHint(R.string.password);

        login.setOnClickListener(new MyListener_login());
        registered.setOnClickListener(new MyListener_registered());
        jizhumima.setOnClickListener(new MyListener_jizhumima());

        //读取数据库上次登录表，若存在记录，则写入账号
        DBHelper dbHelper = new DBHelper(LoginActivity.this, "LifeHelper_DB", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        System.out.println("数据库打开成功");

        Cursor cursor_last_login = db.query("last_login",null,null,null,null,null,null);
        System.out.println("数据库查询成功");
        if (cursor_last_login.moveToFirst())
        {
            String phone_value = cursor_last_login.getString(cursor_last_login.getColumnIndex("phone"));
            System.out.println("数据库查询成功:"+phone_value);
            phone.setText(phone_value);
            System.out.println("phonetext设置成功:");

            try{
                //读取数据库记录账号密码表，是否记录账号值，判断是否写入账号密码
                Cursor cursor_record_password = db.query("record_password",null,"phone="+phone_value,null,null,null,null);
                System.out.println("phonetext设置成功:");
                cursor_record_password.moveToFirst();
                String password_value = cursor_record_password.getString(cursor_record_password.getColumnIndex("password"));

                System.out.println("phonetext设置成功:");
                String record_value = cursor_record_password.getString(cursor_record_password.getColumnIndex("record"));

                System.out.println("数据库对比成功");
                if (record_value.equals("1"))
                {
                    password.setText(password_value);
                    jizhumima.setChecked(true);
                }
                else
                {
                    jizhumima.setChecked(false);
                }
            }catch(Exception e){}
        }
        db.close();


    }
    class MyListener_login implements View.OnClickListener{
        private int phone_value;
        private String password_value;
        private String password_DB;
        private int phone_DB;
        @Override
        public void onClick(View v) {
            textView_loginppp.setText("");
            DBHelper dbHelper = new DBHelper(LoginActivity.this, "LifeHelper_DB", null, 1);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            System.out.println("login按钮单击执行");
            //读取输入账号密码
            phone_value = Integer.valueOf(phone.getText().toString());
            password_value = password.getText().toString();
            System.out.println("读取输入账号密码成功");
            //读取数据库账号密码

            try{
                Cursor cursor_pho_pas = db.query("user",null,"phone="+phone_value,null,null,null,null);
                cursor_pho_pas.moveToFirst();
                phone_DB = Integer.valueOf(cursor_pho_pas.getString(cursor_pho_pas.getColumnIndex("phone")));
                password_DB = cursor_pho_pas.getString(cursor_pho_pas.getColumnIndex("password"));

                System.out.println("读取数据库账号密码成功:"+phone_DB+password_DB);
                System.out.println("读取输入账号密码成功:"+phone_value+password_value);
                if (phone_DB == phone_value)
                    System.out.println("账号一致");
                if (password_value.equals(password_DB))
                    System.out.println("密码一致");
            }catch (Throwable e){
                textView_loginppp.setText("");
            }

            //对比账号密码是否正确，如果正确，检查是否记住密码，否则给出消息密码错误请重试

            if (phone_value == phone_DB && password_value.equals(password_DB))
            {
                DBHelper dbHelper1 = new DBHelper(LoginActivity.this, "LifeHelper_DB", null, 1);
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                if (jizhumima.isChecked()) {
                    //写数据库，记住密码表，记录账号，记录密码，记录是否记住密码
                    System.out.println("记住密码被选中，执行表插入:");
                    ContentValues values = new ContentValues();
                    values.put("phone", phone_value);
                    values.put("password", password_value);
                    values.put("record", "1");

                    db.insert("record_password", null, values);
                    System.out.println("record_password表插入成功:");
                }else{
                    db1.execSQL("delete from record_password");
                    System.out.println("记住密码未选中,已清空record_password表:");
                }

                //写数据库，记录上次登录表，写入当前登录账号
                System.out.println("写数据库，记录上次登录表，写入当前登录账号:");
                db1.execSQL("delete from last_login");
                System.out.println("清空表last_login完成:");
                ContentValues values = new ContentValues();
                values.put("phone", phone_value);
                db.insert("last_login", null, values);
                System.out.println("写数据库，记录上次登录表，写入当前登录账号成功:");
                db.close();


                //跳转主页
                System.out.println("跳转主页");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent); //执行
                System.out.println("跳转主页执行完毕");
                News_add();
                finish();
            }else{
                //给出消息密码错误
                textView_loginppp.setText("账户信息\n错误或不存在\n重新输入\n或点击注册");
            }

        }
        private void News_add() {
            DBHelper dbHelper = new DBHelper(LoginActivity.this, "LifeHelper_DB", null, 1);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor_get_phone = db.query("last_login",null,null,null,null,null,null);
            cursor_get_phone.moveToFirst();
            String phone = cursor_get_phone.getString(cursor_get_phone.getColumnIndex("phone"));
            ContentValues values = new ContentValues();
            values.put("id", 0);
            values.put("name", "腾讯新闻");
            values.put("phone", phone);
            values.put("url", "https://news.qq.com/");
            db.insert("news", null, values);
            values.put("id", 1);
            values.put("name", "中华新闻");
            values.put("phone", phone);
            values.put("url", "http://www.chinanews.com/");
            db.insert("news", null, values);
            values.put("id", 2);
            values.put("name", "网易新闻");
            values.put("phone", phone);
            values.put("url", "https://news.163.com/");
            db.insert("news", null, values);
            values.put("id", 3);
            values.put("name", "央视新闻");
            values.put("phone", phone);
            values.put("url", "http://news.cctv.com/");
            db.insert("news", null, values);
            System.out.println("news表插入成功");

            values.put("id", 0);
            values.put("name", "齐齐哈尔—建华区");
            values.put("phone", phone);
            values.put("url", "http://m.weather.com.cn/mweather/101050212.shtml");
            db.insert("weather", null, values);
            values.put("id", 1);
            values.put("name", "北京");
            values.put("phone", phone);
            values.put("url", "http://m.weather.com.cn/mweather/101010100.shtml");
            db.insert("weather", null, values);
            values.put("id", 2);
            values.put("name", "南京");
            values.put("phone", phone);
            values.put("url", "http://m.weather.com.cn/mweather/101190101.shtml");
            db.insert("weather", null, values);

            ContentValues values1 = new ContentValues();
            values1.put("id", 1);
            values1.put("name", "上海");/* 1dn/101050212 */
            values1.put("url_pic", "101020100");
            db.insert("area_code", null, values1);
            values1.put("id", 2);
            values1.put("name", "天津");/* 1dn/101050212 */
            values1.put("url_pic", "101030100");
            db.insert("area_code", null, values1);
            values1.put("id", 3);
            values1.put("name", "重庆");/* 1dn/101050212 */
            values1.put("url_pic", "101040100");
            db.insert("area_code", null, values1);
            values1.put("id", 4);
            values1.put("name", "哈尔滨");/* 1dn/101050212 */
            values1.put("url_pic", "101050101");
            db.insert("area_code", null, values1);
            values1.put("id", 5);
            values1.put("name", "长春");/* 1dn/101050212 */
            values1.put("url_pic", "101060101");
            db.insert("area_code", null, values1);
            values1.put("id", 6);
            values1.put("name", "沈阳");/* 1dn/101050212 */
            values1.put("url_pic", "101070101");
            db.insert("area_code", null, values1);
            values1.put("id", 7);
            values1.put("name", "呼和浩特");/* 1dn/101050212 */
            values1.put("url_pic", "101080101");
            db.insert("area_code", null, values1);
            values1.put("id", 8);
            values1.put("name", "石家庄");/* 1dn/101050212 */
            values1.put("url_pic", "101090101");
            db.insert("area_code", null, values1);
            values1.put("id", 9);
            values1.put("name", "太原");/* 1dn/101050212 */
            values1.put("url_pic", "101100101");
            db.insert("area_code", null, values1);
            values1.put("id", 10);
            values1.put("name", "西安");/* 1dn/101050212 */
            values1.put("url_pic", "101110101");
            db.insert("area_code", null, values1);
            values1.put("id", 11);
            values1.put("name", "济南");/* 1dn/101050212 */
            values1.put("url_pic", "101120101");
            db.insert("area_code", null, values1);
            values1.put("id", 12);
            values1.put("name", "乌鲁木齐");/* 1dn/101050212 */
            values1.put("url_pic", "101130101");
            db.insert("area_code", null, values1);

            db.close();
        }
    }
    class MyListener_jizhumima implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            textView_loginppp.setText("");
        }
    }
    class MyListener_registered implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            textView_loginppp.setText("");
            Intent intent = new Intent(LoginActivity.this, RegisteredActivity.class);
            startActivity(intent); //执行
            System.out.println("跳转注册页面执行完毕");
        }
    }

}
