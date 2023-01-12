package edu.ewubd.login_pagewithforgetpassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public TextView register, banner;
    private EditText editTextEmail, editTextPAssword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    DatabaseReference databaseReference;
    int b;
    Query Admin_check = null;
    Query User_check = null;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        signIn = (Button) findViewById(R.id.sign_in);
        signIn.setOnClickListener(this);

        editTextEmail= (EditText) findViewById(R.id.Email_edit_text_Activity_id);
        editTextPAssword = (EditText) findViewById(R.id.Password_edit_text_Activity_id);

        progressBar = (ProgressBar)findViewById(R.id.Progressbar_amain);
        mAuth = FirebaseAuth.getInstance();
        banner = findViewById(R.id.banner);
        banner.setOnClickListener(v->fin());


    }

    private int fin() {
        b = 1;
        return b;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;

            case R.id.sign_in:
                userLogin();
                break;

        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPAssword.getText().toString().trim();

       if(email.isEmpty()){
           editTextEmail.setError("Email is required!");
           editTextEmail.requestFocus();
           return;

       }
       if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           editTextEmail.setError("Please enter a valid email!");
           editTextEmail.requestFocus();
           return;
       }
      if(password.isEmpty()){
          editTextPAssword.setError("Password is required");
          editTextPAssword.requestFocus();
          return;
      }
      if(password.length()<6){
          editTextPAssword.setError("Min password length is 6 characters!");
          editTextPAssword.requestFocus();
          return;
      }
progressBar.setVisibility(View.VISIBLE);

    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {

              if(task.isSuccessful()){
                  //redirect to user profile

                  startActivity(new Intent(MainActivity.this,Profile.class));
                  //String root = String.valueOf(databaseReference.child(email).getRoot());
                //  System.out.println("PRint Roote = "+root);
                  progressBar.setVisibility(View.GONE);
              }else{
                  Toast.makeText(MainActivity.this, " Failed to login!Please check your credentials", Toast.LENGTH_LONG).show();
                  progressBar.setVisibility(View.GONE);
              }


          }
      });








        progressBar.setVisibility(View.GONE);

    }


}