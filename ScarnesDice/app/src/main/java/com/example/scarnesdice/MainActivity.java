package com.example.scarnesdice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int userOverall;
    private int userTurn;
    private int comOverall;
    private int comTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userOverall = userTurn = comTurn = comOverall = 0;
        TextView textView = findViewById(R.id.textView);
        textView.setText("Your score: " + userOverall + " Computer score: " + comOverall);
    }

    public int get() {
        Random random = new Random();
        int x = random.nextInt(6) + 1;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        String pic = "dice" + x;
        int pid = getResources().getIdentifier(pic, "drawable", getPackageName());
        imageView.setImageDrawable(getResources().getDrawable(pid));
        return x;
    }

    public void roll(View view) {
        int x = get();
        if (x == 1) userTurn = 0;
        else userTurn += x;
        TextView textView = findViewById(R.id.textView);
        textView.setText("Your score: " + userOverall + " Computer score: " + comOverall + " Your turn score: " + userTurn);
        if(x == 1) computerTurn();
    }

    public void reset(View view) {
        userOverall = userTurn = comTurn = comOverall = 0;
        TextView textView = findViewById(R.id.textView);
        textView.setText("Your score: " + userOverall + " Computer score: " + comOverall);
    }

    public void hold(View view) {
        userOverall += userTurn;
        userTurn = 0;
        TextView textView = findViewById(R.id.textView);
        textView.setText("Your score: " + userOverall + " Computer score: " + comOverall);
        computerTurn();
    }

    public int flag = 0;
    public void computerTurn() {
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        button.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        flag = 1;
        timerHandler.postDelayed(timerRunnable, 1000);
    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            int x = get();
            if (x == 1) comTurn = 0;
            else comTurn += x;
            TextView textView = findViewById(R.id.textView);
            textView.setText("Your score: " + userOverall + " Computer score: " + comOverall + " Computer turn score: " + comTurn);
            if (x == 1 || comTurn >= 20) {
                comOverall += comTurn;
                comTurn = 0;
                flag = 0;
            }
            if (flag == 1) timerHandler.postDelayed(this, 1000);
            else timerHandler.postDelayed(end, 1000);
        }
    };
    Runnable end = new Runnable() {
        @Override
        public void run() {
            TextView textView = findViewById(R.id.textView);
            textView.setText("Your score: " + userOverall + " Computer score: " + comOverall);
            Button button = (Button) findViewById(R.id.button);
            Button button2 = (Button) findViewById(R.id.button2);
            Button button3 = (Button) findViewById(R.id.button3);
            button.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
        }
    };
}