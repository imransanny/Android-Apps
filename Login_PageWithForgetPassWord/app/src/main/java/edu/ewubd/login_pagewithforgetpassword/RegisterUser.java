package edu.ewubd.login_pagewithforgetpassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView banner, registerUser;
    private EditText editTextFullName, editTextAge, editTextEmail, editTExtPassword;
    private  ProgressBar progressBar;
    DatabaseReference databaseReference;
    int a;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(v->fun());


        registerUser = (Button) findViewById(R.id.Register_user);
        registerUser.setOnClickListener(this);


        editTextFullName = (EditText) findViewById(R.id.Name_edit_text_register_id);

        editTextAge = (EditText) findViewById(R.id.Age_edit_text_register_id);
        editTextEmail = (EditText) findViewById(R.id.Email_edit_text_register_id);
        editTExtPassword = (EditText) findViewById(R.id.Password_edit_text_register_id);


    progressBar = (ProgressBar) findViewById(R.id.Progressbar_registeruser);





    }

    private int fun() {
         a = 1;
        return a;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.banner:
          startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.Register_user:
                registerUser();
                break;
        }

    }

    private void registerUser() {

    String email = editTextEmail.getText().toString().trim();
    String password = editTExtPassword.getText().toString().trim();
    String fullnamee = editTextFullName.getText().toString().trim();
    String age = editTextAge.getText().toString().trim();

    if(fullnamee.isEmpty()){
        editTextFullName.setError("Full name is required");
        return;
    }if(age.isEmpty()){
        editTextFullName.setError("Age is required");
        return;
    }
        if(email.isEmpty()){
            editTextFullName.setError("Email is required");
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please valid email address");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTExtPassword.setError("Password is required");
            editTExtPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editTExtPassword.setError("Min Password length should be 6 characters!");
            editTExtPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

       //USER REGISTER
        //==============================================================

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                           if(a == 1) {

                               User user = new User(fullnamee, age, email);

                               FirebaseDatabase.getInstance().getReference("User")
                                       .child(email)
                                       .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {

                                               if (task.isSuccessful()) {
                                                   Toast.makeText(RegisterUser.this, "User has been registerd successfully!", Toast.LENGTH_LONG).show();
                                                   progressBar.setVisibility(View.GONE);

                                                   //redirect to login layout
                                               } else {
                                                   Toast.makeText(RegisterUser.this, "Failed to register! Try again", Toast.LENGTH_LONG).show();
                                                   progressBar.setVisibility(View.GONE);
                                               }

                                           }
                                       });
                               progressBar.setVisibility(View.GONE);
                           }else{
                               System.out.println("aDIMIN");
                               Admin admin = new Admin(fullnamee, age, email);

                               databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
                               System.out.println("aDIMIN1");

                               databaseReference.child(fullnamee).setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {


                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {

                                               System.out.println("aDIMIN12");
                                               if (task.isSuccessful()) {
                                                   System.out.println("aDIMIN15");
                                                   Toast.makeText(RegisterUser.this, "User has been registerd successfully!", Toast.LENGTH_LONG).show();
                                                   progressBar.setVisibility(View.GONE);

                                                   //redirect to login layout
                                               } else {
                                                   System.out.println("aDIMIN16");
                                                   Toast.makeText(RegisterUser.this, "Failed to register! Try again", Toast.LENGTH_LONG).show();
                                                   progressBar.setVisibility(View.GONE);
                                               }

                                           }
                           });
                           }
                        }else{
                            System.out.println("aDIMIN17");
                            Toast.makeText(RegisterUser.this,"Failed to register! Try again", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



    }




}