package com.example.lifehelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Add_mainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView help_feedback;
    private Button button_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_main);

        help_feedback = (TextView)findViewById(R.id.help_feedback);
        button_send = (Button)findViewById(R.id.button_send);

        button_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (help_feedback.getText().toString().equals("")){
            Intent email = new Intent(android.content.Intent.ACTION_SEND);
//邮件发送类型：无附件，纯文本
            email.setType("plain/text");
//邮件接收者（数组，可以是多位接收者）
            String[] emailReciver = new String[]{"3243881379@qq.com"};

            String  emailTitle = "软件使用反馈建议";
            String  emailContent = help_feedback.getText().toString();
//设置邮件地址
            email.putExtra(android.content.Intent.EXTRA_EMAIL, emailReciver);
//设置邮件标题
            email.putExtra(android.content.Intent.EXTRA_SUBJECT, emailTitle);
//设置发送的内容
            email.putExtra(android.content.Intent.EXTRA_TEXT, emailContent);
            //调用系统的邮件系统
            startActivity(Intent.createChooser(email, "邮件发送"));
            finish();
        }
    }
}