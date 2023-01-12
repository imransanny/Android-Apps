package edu.ewubd.myapplicationthreadtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
Handler mHandeler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandeler= new Handler();
        new Thread(new Runnable(){
            public  void run(){
                for(int i=0 ; i<=1000;i++){
                    System.out.println("A");
                }


                mHandeler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();

        for(int i=0 ; i<=10;i++){
            System.out.println("B");
        }

    }
}