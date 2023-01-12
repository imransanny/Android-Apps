package edu.ewubd.firebase_login;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class Sign_UP extends AppCompatActivity implements View.OnClickListener {

    private EditText signUPEmailEditText, SignUPPasswordText;
    private TextView SiginInText;
    private Button SignUPButton;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        this.setTitle("Sign in Activity");



// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        signUPEmailEditText = findViewById(R.id.emailedit_text_id);
        SignUPPasswordText = findViewById(R.id.password_edit_text_id);
        SiginInText = findViewById(R.id.last_id);
        SignUPButton = findViewById(R.id.signup_button_id);
        progressBar = findViewById(R.id.progressbar_id2);

        SiginInText.setOnClickListener(this);
        SignUPButton.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
           startActivity(new Intent(Sign_UP.this,Sign_In.class));
        }
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signup_button_id:
                userRegister();

                break;

            case R.id.last_id:
                Intent i = new Intent(getApplicationContext(), Sign_UP.class);
                startActivity(i);
                break;



        }



    }

    private void userRegister() {

        String email = signUPEmailEditText.getText().toString().trim();
        String password =SignUPPasswordText.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty())
        {
            signUPEmailEditText.setError("Enter an email address");
            signUPEmailEditText.requestFocus(); //cursor ta oikhane cholejabe
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())//@ use na korle amail a
        {
            signUPEmailEditText.setError("Enter a valid email address");
            signUPEmailEditText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(email.isEmpty())
        {
            SignUPPasswordText.setError("Enter a password");
            SignUPPasswordText.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            SignUPPasswordText.setError("Minimum length of a password should be 6 ");
            SignUPPasswordText.requestFocus();
            return;
        }
         progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Toast.makeText(getApplicationContext(), "Register is successfull",Toast.LENGTH_LONG).show();
                            finish();//ekbar sign in hole back korle ar ashbe na
                            Intent i = new Intent(getApplicationContext(), Home.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                                if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(getApplicationContext(), "User is already Registered",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error : "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                        }
                    }
                });






    }

}


