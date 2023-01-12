
package edu.ewubd.cse2020160119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SIGNUP extends AppCompatActivity {

    EditText name,email, phone, user_id, password,re_enter_pass;
    CheckBox remember_user, remember_password;
    Button exit, go;
    TableRow row1, row2 , row3, row4 , row5, row6;
    TextView login;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = this.getSharedPreferences("Store_Data_myPref", MODE_PRIVATE);

        String s1 = sp.getString("REMEMBER_USERID", "");
        String s2 = sp.getString("REMEMBER PASSWORD", "");

        //s2 = "nooo" ;
        if(s2.equals("YES")){

            Intent i = new Intent(SIGNUP.this, Home.class);
            startActivity(i);

        } /*else if(s1.equals("NO") && s2.equals("NO")){

            //Intent i = new Intent(this, SIGNUP.class);
            // startActivity(i);
            return;
        } */



        setContentView(R.layout.signup_lab4);





        name = findViewById(R.id.name_id);
        row1 = findViewById(R.id.name_row_id);

        email = findViewById(R.id.email_id);
        row2 = findViewById(R.id.email_row_id);

        phone = findViewById(R.id.phone_id);
        row3 = findViewById(R.id.phone_row_id);

        user_id = findViewById(R.id.user_id);
        row4 = findViewById(R.id.userid_row_id);

        password = findViewById(R.id.password_id);
        row5 = findViewById(R.id.password_row_id);

        re_enter_pass = findViewById(R.id.re_enter_Password_id);
        row6 = findViewById(R.id.re_enter_password_row_id);

        remember_user = findViewById(R.id.checkbox_remember_userID);
        remember_password = findViewById(R.id.checkbox_remember_password);


        login = findViewById(R.id.login_id);
        login.setOnClickListener(view -> HideRow(view));

        exit = findViewById(R.id.exit_button_id);
        exit.setOnClickListener(View -> finish());

        go = findViewById(R.id.go_button_id);
        go.setOnClickListener((view -> FuncSave()));

    }

    public void HideRow(View view){
        row1.setVisibility(View.GONE);
        row2.setVisibility(View.GONE);
        row3.setVisibility(View.GONE);
        row6.setVisibility(View.GONE);

    }

    public void FuncSave(){
        String name1 = name.getText().toString().trim();
        System.out.println(name1);
        System.out.println(email.getText().toString().trim());
        System.out.println(phone.getText().toString().trim());
        String user_id1 = user_id.getText().toString();
        System.out.println(user_id1.trim());
        System.out.println(password.getText().toString().trim());
        System.out.println(re_enter_pass.getText().toString().trim());




        if(user_id1.isEmpty()){
            user_id.setError("" +
                    "User id is required");
            user_id.requestFocus();
            return;
        }






        if(remember_user.isChecked()){
            System.out.println("click user"); }

        if(remember_password.isChecked()){
            System.out.println("click pass"); }

//lab5
        SharedPreferences sp = this.getSharedPreferences("Store_Data_myPref", MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();



        e.putString("NAME", name.getText().toString());
        e.putString("EMAIL", email.getText().toString());
        e.putString("PHONE", phone.getText().toString());
        e.putString("USER_ID", user_id.getText().toString());
        e.putString("PASSWOED", password.getText().toString());
        e.putString("RE_ENTER PASSWORD", re_enter_pass.getText().toString());

        if(remember_user.isChecked()){
            e.putString("REMEMBER_USERID", "YES");
            System.out.println("click user yes");
        }else{
            e.putString("REMEMBER_USERID", "NO");
            System.out.println("click user no");
        }

        if(remember_password.isChecked()){
            e.putString("REMEMBER PASSWORD", "YES");
            System.out.println("click pass yes");
        }else{
            e.putString("REMEMBER PASSWORD", "NO");
            System.out.println("click pass no");
        }
        e.apply();

        Intent i = new Intent(SIGNUP.this, Home.class);
        startActivity(i);
        return;

    }


}
