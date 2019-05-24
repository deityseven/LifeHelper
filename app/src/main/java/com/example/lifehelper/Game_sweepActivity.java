package com.example.lifehelper;

import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lifehelper.R;

public class Game_sweepActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private GridLayout grid;
    private int row = 10;
    private int column = 10;
    private int num = 100;
    private int[][] Bat,Gat;
    private int
            simple = 10,
            general = 25 ,
            difficult = 50,
            difficulty_int;

    private final static int COUNT = 1;
    private TextView countDown;
    private TextView residue;
    private Button difficulty;
    private ImageButton face;

    private CountDownTimer timer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_sweep);

        difficulty = (Button)findViewById(R.id.Button_difficulty);
        residue = (TextView)findViewById(R.id.textView_residue);
        face = (ImageButton)findViewById(R.id.imageButton_face);
        difficulty.setOnClickListener(new difficultyLisenter());
        face.setOnClickListener(new faceLisenter());

    }
    private void initView(CountDownTimer timer) {
        countDown = (TextView)findViewById(R.id.textView_countDown);
        //CountDownTimer构造器的两个参数分别是第一个参数表示总时间，第二个参数表示间隔时间。
        //意思就是每隔xxx会回调一次方法onTick，然后xxx之后会回调onFinish方法。
        timer = new CountDownTimer(100*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDown.setText(String.valueOf(num));
                num--;
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }
    @Override
    public void onClick(View view) {
        Button button = (Button) findViewById(view.getId());
        if (button.getText().toString().equals(""))
        {
            int text = 0;
            int BOOM = 0;
            System.out.println(view.getId());

            for (int i = 0 ; i<10 ;i++)
            {
                for (int j = 0 ; j<10 ;j++){
                    System.out.print(Bat[i][j]);
                }
                System.out.println("");
            }
            for (int i = 0 ; i<10 ;i++)
            {
                for (int j = 0 ; j<10 ;j++){
                    System.out.print(Gat[i][j]);
                }
                System.out.println("");
            }
            int k = view.getId();
            int n = k / 10;
            int b = k % 10;

            BOOM = Bat[n][b];
            //Button button = (Button)findViewById(view.getId());

            if (BOOM == 1){
                button.setText("*");
                button.setBackgroundResource(R.drawable.bomb);
                game_over();
            }else {
                text = panduan(text,n,b);
                if (text ==0 ){

                    spread_blank(n,b,view.getId());

                    button.setText("");
                    button.setBackgroundColor(Color.WHITE);
                }
                else {
                    System.out.println("text:"+text);
                    button.setText(Integer.toString(text));
                    button.setBackgroundColor(Color.WHITE);
                }

            }
            //button.setBackgroundColor(Color.WHITE);
            button.setClickable(false);
            button.setLongClickable(false);
            //System.out.println(view.);
            //R.id.(view.getId());
        }
        else
        {

        }

    }
    private void spread_blank(int n,int b,int id) {
        Button button = (Button)findViewById(id);
        Button button1 = (Button)findViewById((n-1)*10 + b);
        Button button2 = (Button)findViewById((n+1)*10 + b);
        Button button3 = (Button)findViewById((n)*10 + (b-1));
        Button button4 = (Button)findViewById((n)*10 + (b+1));

        System.out.println(n+":"+b+":"+id);
        try{
            if (Gat[n-1][b] == 0){
                id = (n-1)*10 + b;

                button1.setBackgroundColor(Color.WHITE);
                Gat[n][b] = -1;
            }
        }catch (Throwable e ){
        }

        try{
            if (Gat[n+1][b] == 0){
                id = (n+1)*10 + b;

                button2.setBackgroundColor(Color.WHITE);
                Gat[n][b] = -1;
            }
        }catch (Throwable e ){
        }

        try{
            if (Gat[n][b-1] == 0){
                id = (n)*10 + (b-1);

                button3.setBackgroundColor(Color.WHITE);
                Gat[n][b] = -1;
            }
        }catch (Throwable e ){

        }

        try{
            if (Gat[n][b+1] == 0){
                id = (n)*10 + (b+1);

                button4.setBackgroundColor(Color.WHITE);
                Gat[n][b] = -1;
            }
        }catch (Throwable e ){

        }
        return ;
    }
    private int panduan(int text,int n,int b) {
        try{
            if (Bat[n-1][b-1] == 1){
                text +=1 ;
            }

        }catch (Throwable e ){
            //System.out.println("数组读取失败");
        }
        try{
            if (Bat[n-1][b] == 1){
                text +=1 ;
            }

        }catch (Throwable e ){
            //System.out.println("数组读取失败");
        }
        try{
            if (Bat[n-1][b+1] == 1){
                text +=1 ;
            }

        }catch (Throwable e ){
            //System.out.println("数组读取失败");
        }
        try{
            if (Bat[n][b-1] == 1){
                text +=1 ;
            }

        }catch (Throwable e ){
            //System.out.println("数组读取失败");
        }
        try{
            if (Bat[n][b+1] == 1){
                text +=1 ;
            }

        }catch (Throwable e ){
            //System.out.println("数组读取失败");
        }
        try{
            if (Bat[n+1][b-1] == 1){
                text +=1 ;
            }

        }catch (Throwable e ){
            //System.out.println("数组读取失败");
        }
        try{
            if (Bat[n+1][b] == 1){
                text +=1 ;
            }

        }catch (Throwable e ){
            //System.out.println("数组读取失败");
        }
        try{
            if (Bat[n+1][b+1] == 1){
                text +=1 ;
            }

        }catch (Throwable e ){
            //System.out.println("数组读取失败");
        }
        return text;
    }
    public void initDate (int[][] Bat,int[][] Gat,int difficulty){
        for (int i = 0 ; i<10 ;i++)
        {
            for (int j = 0 ; j<10 ;j++){
                Bat[i][j] = 0;
                Gat[i][j] = 0;
            }
        }
        for (int i = 0 ; i<=difficulty ;i++)
        {
            int k = (int)(Math.random()*100);
            if (k<10){
                Bat[0][k] = 1;
            }else {
                int n = k / 10;
                int b = k % 10;
                Bat[n][b] = 1;
            }
        }
        for (int i = 0 ; i<10 ;i++)
        {
            for (int j = 0 ; j<10 ;j++){
                if (Bat[i][j] == 1){
                    Gat[i][j] = 9;
                }
            }
        }

        for (int i = 0 ; i<10 ;i++)
        {
            for (int j = 0 ; j<10 ;j++){
                int text = 0;
                if (Gat[i][j] == 9)
                {

                }else {
                    try{
                        if (Gat[i-1][j-1] == 9){

                        }

                    }catch (Throwable e ){
                        //System.out.println("数组读取失败");
                    }
                    try{
                        if (Gat[i-1][j] == 9){
                            text +=1 ;
                        }

                    }catch (Throwable e ){
                        //System.out.println("数组读取失败");
                    }
                    try{
                        if (Gat[i-1][j+1] == 9){
                            text +=1 ;
                        }

                    }catch (Throwable e ){
                        //System.out.println("数组读取失败");
                    }
                    try{
                        if (Gat[i][j-1] == 9){
                            text +=1 ;
                        }

                    }catch (Throwable e ){
                        //System.out.println("数组读取失败");
                    }
                    try{
                        if (Gat[i][j+1] == 9){
                            text +=1 ;
                        }

                    }catch (Throwable e ){
                        //System.out.println("数组读取失败");
                    }
                    try{
                        if (Gat[i+1][j-1] == 9){
                            text +=1 ;
                        }

                    }catch (Throwable e ){
                        //System.out.println("数组读取失败");
                    }
                    try{
                        if (Gat[i+1][j] == 9){
                            text +=1 ;
                        }

                    }catch (Throwable e ){
                        //System.out.println("数组读取失败");
                    }
                    try{
                        if (Gat[i+1][j+1] == 9){
                            text +=1 ;
                        }

                    }catch (Throwable e ){
                        //System.out.println("数组读取失败");
                    }
                    Gat[i][j] = text;
                }

            }
        }

    }
    @Override
    public boolean onLongClick(View view) {
        Button button = (Button)findViewById(view.getId());
        System.out.println(button.getText().toString().equals("+"));
        if (button.getText().toString().equals("+"))
        {
            button.setText("");
            button.setBackgroundResource(android.R.drawable.btn_default);
            System.out.println("yse");
        }else {
            button.setText("+");
            button.setBackgroundResource(R.drawable.flag);
            //button.setClickable(false);

            System.out.println("button.setClickable(false)");
        }




        return true;
    }
    class difficultyLisenter implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (difficulty.getText().toString()){
                case "普通":
                    difficulty.setText("一般");
                    residue.setText(String.valueOf(general));
                    break;
                case "一般":
                    difficulty.setText("困难");
                    residue.setText(String.valueOf(difficult));
                    break;
                case "困难":
                    difficulty.setText("普通");
                    residue.setText(String.valueOf(simple));
                    break;
            }

        }
    }
    class faceLisenter implements View.OnClickListener{
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View view) {

            String difficulty_value = difficulty.getText().toString();
            String residue_value = residue.getText().toString();
            num = 100;
            initView(timer);
            start();

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void start(){

        WindowManager wm1 = this.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();
        int height1 = wm1.getDefaultDisplay().getHeight();

        grid = (GridLayout) findViewById(R.id.GridLayout);
        grid.setRowCount(row);
        grid.setColumnCount(column);

        grid.setBackgroundColor(Color.RED);

        Bat = new int[row][row];
        Gat = new int[row][row];
        difficulty_int = Integer.valueOf(residue.getText().toString());
        initDate(Bat,Gat,difficulty_int);

        try{
            for (int i = 0; i < row ; i++)
            {
                for (int j = 0; j < column ; j++) {
                    Button button = (Button)findViewById(i*10+j);
                    //button.setBackgroundColor(Color.BLUE);
                    grid.removeAllViews();
                    //button.setText(Integer.toString(i));
                    /*
                    button.setId(i*10+j);
                    button.setOnClickListener(this);
                    button.setOnLongClickListener(this);
                    GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                    params.width = 0;
                    params.height = 0;
                    params.rowSpec = GridLayout.spec(i, 1f);
                    params.columnSpec = GridLayout.spec(j, 1f);

                    button.setGravity(Gravity.CENTER);
                    button.setBackgroundResource(android.R.drawable.btn_default);
                    // 设置边距
                    params.setMargins(1,1,1,1);
                    // 设置文字
                    //button.setText(mStrings[i]);
                    // 添加item
                    button.setLayoutParams(params);
                    grid.addView(button);
                    */
                }
            }
        }catch (Exception e){

        }

        for (int i = 0; i < row ; i++)
        {
            for (int j = 0; j < column ; j++) {
                Button button = new Button(this);
                //button.setBackgroundColor(Color.BLUE);

                //button.setText(Integer.toString(i));
                button.setId(i*10+j);
                button.setOnClickListener(this);
                button.setOnLongClickListener(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = 0;
                params.rowSpec = GridLayout.spec(i, 1f);
                params.columnSpec = GridLayout.spec(j, 1f);

                button.setGravity(Gravity.CENTER);
                button.setBackgroundResource(android.R.drawable.btn_default);
                // 设置边距
                params.setMargins(1,1,1,1);
                // 设置文字
                //button.setText(mStrings[i]);
                // 添加item
                button.setLayoutParams(params);
                grid.addView(button);
            }
        }
    }
    public void game_over(){
        for (int i = 0; i < row ; i++)
        {
            for (int j = 0; j < column ; j++) {
                Button button = (Button)findViewById(i*10+j);
                button.performClick();

                //button.setBackgroundColor(Color.BLUE);
                /*
                //button.setText(Integer.toString(i));
                button.setId(i*10+j);
                button.setOnClickListener(this);
                button.setOnLongClickListener(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = 0;
                params.rowSpec = GridLayout.spec(i, 1f);
                params.columnSpec = GridLayout.spec(j, 1f);

                button.setGravity(Gravity.CENTER);
                button.setBackgroundResource(android.R.drawable.btn_default);
                // 设置边距
                params.setMargins(1,1,1,1);
                // 设置文字
                //button.setText(mStrings[i]);
                // 添加item
                button.setLayoutParams(params);
                grid.addView(button);
                */
            }
        }
    }
}