package edu.ewubd.calculatordesign;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginPAge extends AppCompatActivity {


    SharedPreferences Sprefs;
    EditText id;
    EditText name;
    EditText age;
    String Value;
    private String prefName = "report";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id=(EditText) findViewById(R.id.editText1);
        name=(EditText) findViewById(R.id.editText2);
        age=(EditText) findViewById(R.id.editText3);
        Button save=(Button) findViewById(R.id.button1);
        Button select=(Button) findViewById(R.id.button2);
        Button update=(Button) findViewById(R.id.button3);
        Button delete=(Button) findViewById(R.id.button4);
        Button clear=(Button) findViewById(R.id.button5);


        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Sprefs = getSharedPreferences(prefName, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = Sprefs.edit();

                if (id.getText().toString().equals("")||name.getText().toString().equals("")||age.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Please Fill All the Fields",Toast.LENGTH_SHORT).show();
                }
                else if (!Sprefs.getString("id"+id.getText().toString(),"").toString().equals("")){
                    Toast.makeText(getBaseContext(), "User is already Registered",Toast.LENGTH_SHORT).show();
                }
                else {
                    //---save the values in the EditText view to preferences---
                    editor.putString("id"+id.getText().toString(),id.getText().toString());
                    editor.putString("name"+id.getText().toString(),name.getText().toString());
                    editor.putString("age"+id.getText().toString(),age.getText().toString());
                    editor.commit();//---saves the values---
                    Toast.makeText(getBaseContext(), "User Details Registered",Toast.LENGTH_SHORT).show();
                    id.setText("");
                    name.setText("");
                    age.setText("");

                }
            }
        });

        select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Sprefs = getSharedPreferences(prefName, Context.MODE_PRIVATE);
                //Value=Sprefs.getString("id"+id.getText().toString(),"");
                if(id.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(), "Please Enter User ID",Toast.LENGTH_SHORT).show();
                    id.setText("");
                    name.setText("");
                    age.setText("");

                }
                else if(Sprefs.getString("id"+id.getText().toString(),"").toString().equals("")){
                    Toast.makeText(getBaseContext(), "No such User ID Exits. Provide Valid User ID",Toast.LENGTH_SHORT).show();
                }
                else{
                    //name.setText(Sprefs.getString("name"+name.getText().toString(),"").toString());
                    //age.setText(Sprefs.getString("age"+age.getText().toString(),"").toString());
                    name.setText(Sprefs.getString("name"+id.getText().toString(),"").toString());
                    age.setText(Sprefs.getString("age"+id.getText().toString(),"").toString());

                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Sprefs = getSharedPreferences(prefName,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = Sprefs.edit();

                //---save the values in the EditText view to preferences---

                if (id.getText().toString().equals("")||name.getText().toString().equals("")||age.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Please Fill All the Fields",Toast.LENGTH_SHORT).show();
                }
                else if (Sprefs.getString("id"+id.getText().toString(),"").toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Cannot Update: No Such User Exists",Toast.LENGTH_SHORT).show();
                }
                else {

                    //---save the values in the EditText view to preferences---
                    editor.putString("id"+id.getText().toString(),id.getText().toString());
                    editor.putString("name"+id.getText().toString(),name.getText().toString());
                    editor.putString("age"+id.getText().toString(),age.getText().toString());
                    editor.commit();//---saves the values---
                    Toast.makeText(getBaseContext(), "Updated Your Data Successfully",Toast.LENGTH_SHORT).show();
                    id.setText("");
                    name.setText("");
                    age.setText("");
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (id.getText().toString().equals("")||name.getText().toString().equals("")||age.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Please Fill All the Fields",Toast.LENGTH_SHORT).show();
                }
                else if (Sprefs.getString("id"+id.getText().toString(),"").toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Cannot Delete : No Such User ID Exists",Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor ed=Sprefs.edit();
                    ed.clear();// remove all data
                    ed.commit();// save changes
                    Toast.makeText(getBaseContext(), "Deleted Your Data Record Successfully",Toast.LENGTH_SHORT).show();
                    id.setText("");
                    name.setText("");
                    age.setText("");
                }

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                id.setText("");
                name.setText("");
                age.setText("");
            }
        });

    }

}