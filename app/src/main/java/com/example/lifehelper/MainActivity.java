package com.example.lifehelper;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton memo,
            schedule,
            news,
            game,
            add,
            user,
            weather;
    private Handler handler = new Handler();

    private Chronometer chronometer;

    private TextView tx;
    private Thread newThread;
    private Calendar calendar;

    private int year,month,day,hour,minute,second;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindViewById();
        SetOnClickListener();
        //Chronometer_cass();
        Timer_cass();

    }

    private void Timer_cass() {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Calendar calendar = Calendar.getInstance();
                        //tx = (TextView)findViewById(R.id.tx);

                        year = calendar.get(Calendar.YEAR);
                        month = calendar.get(Calendar.MONTH)+1;
                        day = calendar.get(Calendar.DAY_OF_MONTH);

                        hour = calendar.get(Calendar.HOUR_OF_DAY);
                        minute = calendar.get(Calendar.MINUTE);
                        second = calendar.get(Calendar.SECOND);
                        tx.setText(year+"年\n"+month+"月"+day+"日\n"+hour+":"+minute+":"+second);
                        calendar = null;
                    }
                });
            }
        };
        timer.schedule(task,0,1000);
    }

    private void Chronometer_cass() {
        int a=0;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Calendar calendar = Calendar.getInstance();
                        tx = (TextView)findViewById(R.id.tx);
                    /*
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH)+1;
                    day = c.get(Calendar.DAY_OF_MONTH);
                    hour = c.get(Calendar.HOUR_OF_DAY);
                    minute = c.get(Calendar.MINUTE);
                    second = c.get(Calendar.SECOND);*/
                        tx.setText("Calendar获取当前日期"+year+"年"+month+"月"+day+"日"+hour+":"+minute+":"+second);
                        calendar = null;
                    }
                });
            }
        });
        thread.start();

        /*
        final int year = calendar.get(Calendar.YEAR);
//月
        final int month = calendar.get(Calendar.MONTH)+1;
//日
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
//获取系统时间
//小时
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
//分钟
        final int minute = calendar.get(Calendar.MINUTE);
//秒
        final int second = calendar.get(Calendar.SECOND);
            *//*
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //try {
                    Calendar calendar = Calendar.getInstance();
                    tx = (TextView)findViewById(R.id.tx);
                    /*
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH)+1;
                    day = c.get(Calendar.DAY_OF_MONTH);
                    hour = c.get(Calendar.HOUR_OF_DAY);
                    minute = c.get(Calendar.MINUTE);
                    second = c.get(Calendar.SECOND);*/
                    /*tx.setText("Calendar获取当前日期"+calendar.get(Calendar.YEAR)+"年"+calendar.get(Calendar.MONTH)+1+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日"+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND));
                    calendar = null;
                //} catch (Throwable a) {
                    //System.out.println("error");
                //}
          /*  }
        };
        timer.schedule(task,0,1000);
        /*
        newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //这里写入子线程需要做的工作
            }
        });

        System.out.println("chronometer.start();");
        chronometer.setBase(0);
        chronometer.start();*/
    }


    private void SetOnClickListener() {
        memo.setOnClickListener(this);
        schedule.setOnClickListener(this);
        news.setOnClickListener(this);
        game.setOnClickListener(this);
        add.setOnClickListener(this);
        user.setOnClickListener(this);
        weather.setOnClickListener(this);
    }
    private void FindViewById() {
        memo = (ImageButton)findViewById(R.id.imageButton_memo);
        schedule = (ImageButton)findViewById(R.id.imageButton_schedule);
        news = (ImageButton)findViewById(R.id.imageButton_news);
        game = (ImageButton)findViewById(R.id.imageButton_game);
        add = (ImageButton)findViewById(R.id.imageButton_add);
        user = (ImageButton)findViewById(R.id.imageButton_user);
        tx = (TextView)findViewById(R.id.tx);
        weather = (ImageButton)findViewById(R.id.imageButton_weather);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButton_memo:
                Intent intent = new Intent(MainActivity.this, Memo_mianActivity.class);
                startActivity(intent); //执行
                break;
            case R.id.imageButton_schedule:
                Intent intent1 = new Intent(MainActivity.this, Schedule_mianActivity.class);
                startActivity(intent1); //执行
                break;
            case R.id.imageButton_news:
                Intent intent2 = new Intent(MainActivity.this, News_mainActivity.class);
                startActivity(intent2); //执行
                break;
            case R.id.imageButton_game:
                Intent intent3 = new Intent(MainActivity.this, Game_mainActivity.class);
                startActivity(intent3); //执行
                break;
            case R.id.imageButton_add:
                Intent intent4 = new Intent(MainActivity.this, Add_mainActivity.class);
                startActivity(intent4); //执行
                break;
            case R.id.imageButton_user:
                Intent intent5 = new Intent(MainActivity.this, User_mainActivity.class);
                startActivity(intent5); //执行
                break;
            case R.id.imageButton_weather:
                Intent intent6 = new Intent(MainActivity.this, Weather_nationalActivity.class);
                startActivity(intent6); //执行
                break;
            default:
                break;
        }
    }
    class MyListener_ImageButton_memo implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Memo_mianActivity.class);
            startActivity(intent); //执行
            System.out.println("跳转memo_mian执行完毕");
            //finish();
        }
    }
    class MyListener_ImageButton_schedule implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Schedule_mianActivity.class);
            startActivity(intent); //执行
            System.out.println("跳转schedule_main页面执行完毕");
            //finish();
        }
    }
    class MyListener_ImageButton_news implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, News_mainActivity.class);
            startActivity(intent); //执行
            System.out.println("跳转new_main页面执行完毕");
            //finish();
        }
    }
    class MyListener_ImageButton_game implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Game_mainActivity.class);
            startActivity(intent); //执行
            System.out.println("跳转game_main页面执行完毕");
            //finish();
        }
    }



}