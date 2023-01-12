package edu.ewubd.firebase_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();


    }


    //  LOG OUT
    //menu layout ta k java te convert korbo

 public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
 }

 //home page a dukaar por sign out a click korle sign out hobe
@Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.signOutMenu_id){
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent i = new Intent(getApplicationContext(),Sign_In.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }




}


