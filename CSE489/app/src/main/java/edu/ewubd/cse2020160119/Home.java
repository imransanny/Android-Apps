package edu.ewubd.cse2020160119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    TextView event, createnew,upev;
    Button upcoming,logout,exit;


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        event = findViewById(R.id.Event_id);
        createnew = findViewById(R.id.Createsignup_id);
        upcoming = findViewById(R.id.upcoming_home_id);
        upev = findViewById(R.id.UpcomingEvent_id);
        logout = findViewById(R.id.logout_id);
        exit = findViewById(R.id.exxit_id);

        exit.setOnClickListener(view -> finish());
        SharedPreferences sp = this.getSharedPreferences("Store_Data_myPref", MODE_PRIVATE);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              sp.edit().clear().apply();
                Intent i = new Intent(getApplicationContext(), SIGNUP.class);
                startActivity(i);
                finish();
            }
        });

upev.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(getApplicationContext(), UpomingEventActivity.class);
        startActivity(i);
    }
});
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,event_information_lab3.class);
                startActivity(i);
            }
        });


        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,SIGNUP.class);
                startActivity(i);
            }
        });

        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,UpomingEventActivity.class);
                startActivity(i);
            }
        });

    }
}
