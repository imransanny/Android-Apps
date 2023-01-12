package edu.ewubd.firebase_login;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_In extends AppCompatActivity implements View.OnClickListener {

    private EditText signInEmailEditText, SignInPasswordText;
    private TextView signUpTexview;
    private Button SignInButton;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        this.setTitle("Sign in Activity");

        mAuth = FirebaseAuth.getInstance();

        signInEmailEditText = findViewById(R.id.sign_emailedit_text_id);
        SignInPasswordText = findViewById(R.id.signup_password_edit_text_id);
        signUpTexview = findViewById(R.id.signin_text_id);
        SignInButton = findViewById(R.id.signup_button_id);
        progressBar = findViewById(R.id.progressbar_id);

          signUpTexview.setOnClickListener(this);
          SignInButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signup_button_id:
                userLogin();
                break;

            case R.id.signin_text_id:
                Intent i = new Intent(getApplicationContext(), Sign_UP.class); //signin na kora thakle sign up a jabo
                startActivity(i);
                break;



        }



    }

    private void userLogin() {


        String email = signInEmailEditText.getText().toString().trim();
        String password =SignInPasswordText.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty())
        {
            signInEmailEditText.setError("Enter an email address");
            signInEmailEditText.requestFocus(); //cursor ta oikhane cholejabe
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())//@ use na korle amail a
        {
            signInEmailEditText.setError("Enter a valid email address");
            signInEmailEditText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(email.isEmpty())
        {
            SignInPasswordText.setError("Enter a password");
            SignInPasswordText.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            SignInPasswordText.setError("Minimum length of a password should be 6 ");
            SignInPasswordText.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);

       //login using firebase
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);

                if(task.isSuccessful()){
                    //successfully sign in hole new ekta activity te jabe
                    finish();//ekbar sign in hole back korle ar ashbe na
                    Intent i = new Intent(getApplicationContext(), Home.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                }else{
                    Toast.makeText(getApplicationContext(), "Login Unsuccessfull", Toast.LENGTH_LONG).show();
                }

            }
        });




    }

}


