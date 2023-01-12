package edu.ewubd.clockusingthread;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button exit;
    private Handler time_handler;
    long current_second=0;
    long current_minutes;
    long current_hour;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view_timer1_id);
        exit = findViewById(R.id.back_btn);

        textView.setOnClickListener(v->thread_clock());
        exit.setOnClickListener(v->finish());



        long convert_sec = System.currentTimeMillis()/1000;
        current_second = convert_sec % 60;
        current_minutes = ((convert_sec / 60)%60);
        current_hour = (((convert_sec)/(60*60)) % 24)-6;
        String time = current_hour+":"+current_minutes+":"+current_second;
        System.out.println(time);
        textView.setText(time);


    }

    private void thread_clock() {

        time_handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){

                    if(current_second<=58){

                        current_second= current_second+1;
                        String time = current_hour+":"+current_minutes+":"+current_second;
                        System.out.println(time);
                        textView.setText(time);
                        System.out.println(time);

                    }else if(current_second==59){
                        current_second=0;
                        current_minutes=current_minutes+1;
                        String time = current_hour+":"+current_minutes+":"+current_second;
                        System.out.println(time);
                        textView.setText(time);
                        System.out.println(time);

                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }



            }
        }).start();

    }
}