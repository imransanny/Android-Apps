package edu.ewubd.firebase_realtime_database_anisul;

import static java.nio.file.Files.find;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private Button saveData, findData;
    private EditText nameEditText, ageEditText;

    DatabaseReference databaseReference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveData = findViewById(R.id.save_btn_id);
        nameEditText = findViewById(R.id.name_id1);
        ageEditText = findViewById(R.id.age_id_1);
        findData = findViewById(R.id.find_btn_id);
        findData.setOnClickListener(v->find());

        databaseReference = FirebaseDatabase.getInstance().getReference("Student_Store");


        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDATA();
            }
        });
    }

    private void find() {
        String key = databaseReference.getKey();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(key);
        System.out.println(key);
        System.out.println(databaseReference.getRef());
       String data = String.valueOf(databaseReference.getRef());

       String r = String.valueOf(FirebaseDatabase.getInstance().getReference());
        System.out.println(r);



    }

    public void saveDATA(){

        String name = nameEditText.getText().toString().trim();
        String age  = ageEditText.getText().toString().trim();


        //data store
        //==========================================================

        String key = databaseReference.push().getKey();//database a key nibo unique

        Student_Store student_store = new Student_Store(name, age);

        databaseReference.child(key).setValue(student_store);
        Toast.makeText(getApplicationContext(),"Student info added", Toast.LENGTH_LONG).show();



    }



}