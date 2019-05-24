package com.example.lifehelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Game_mainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton sweep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        sweep = (ImageButton) findViewById(R.id.ImageButton_sweep);
        sweep.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ImageButton_sweep:
                Intent intent = new Intent(Game_mainActivity.this, Game_sweepActivity.class);
                startActivity(intent); //执行
                System.out.println("跳转game_sweep页面执行完毕");
                break;
            default:
                break;
        }
    }
}