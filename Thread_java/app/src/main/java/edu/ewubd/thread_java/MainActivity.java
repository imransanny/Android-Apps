package edu.ewubd.thread_java;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    Handler mHandler;
    ProgressDialog mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                mHandler=new Handler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for(int i = 0 ;i<20; i++){
                            System.out.println("AA");
                        }
                    }
                }).start();
        for(int i = 0 ;i<20; i++){
            System.out.println("BB");
        }
            }



    }
