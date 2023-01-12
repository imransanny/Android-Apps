package edu.ewubd.colckusingthread;

import static java.lang.Thread.sleep;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class test extends AppCompatActivity {

    TextView textView;
    Button exit;
    private Handler time_handler;
    long current_second=0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.);

        textView = findViewById(R.id.text_view_timer1_id);



        long convert_sec = System.currentTimeMillis()/1000;
        current_second = convert_sec % 60;
        long current_minutes = ((convert_sec / 60)%60);
        long current_hour = (((convert_sec)/(60*60)) % 24)-6;
        String time = current_hour+":"+current_minutes+":"+current_second;
        System.out.println(time);
       // textView.setText(time);
        System.out.println("time  = "+time);

        time_handler = new Handler();


        new Thread(new Runnable() {
            @Override
            public void run() {

                if(current_hour<=60){
                    while (true) {
                    if (current_second <= 60) {

                        if (current_second == 0) {
                            current_second = current_second + 0;
                            String time = current_hour + ":" + current_minutes + ":" + current_second;
                            System.out.println(time);
                            textView.setText(time);
                            System.out.println(time);
                            current_second = current_second + 1;

                        } else {
                            current_second = current_second + 1;
                            String time = current_hour + ":" + current_minutes + ":" + current_second;
                            System.out.println(time);
                            textView.setText(time);
                            System.out.println(time);
                        }


                        time_handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }, 1000);


                    } }
                        }else{
                            current_second=0;

                        }

                    }

        }).start();


    }
}