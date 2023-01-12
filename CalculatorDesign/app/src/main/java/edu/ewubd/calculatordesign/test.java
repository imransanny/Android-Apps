package edu.ewubd.calculatordesign;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;

public class test extends AppCompatActivity {

    EditText name, email, phone_home, phone_office;
    ImageView image;
    TextView photo, cancle, save;
    String keyoneTime = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        name = findViewById(R.id.name_text_id);
        email = findViewById(R.id.email_text_id);
        phone_home = findViewById(R.id.phone_home_text_id);
        phone_office = findViewById(R.id.office_text_id);
        image = findViewById(R.id.image_id);
        photo = findViewById(R.id.photo_id);
        cancle = findViewById(R.id.cancle_id);
        save = findViewById(R.id.save_id);


        cancle.setOnClickListener(v -> cancl());
        save.setOnClickListener(v ->saveall());


    }
    public void cancl(){
        //loadData("Imran Sunny1670684450926");
        Intent i = new Intent(test.this, loginPAge.class);
        startActivity(i);

    }

    public void saveall(){
        String name1 = name.getText().toString();
        String email1 = email.getText().toString();
        String phone_home1 = phone_home.getText().toString();
        String phone_office1 = phone_office.getText().toString();


        if (keyoneTime.length() ==0){
            keyoneTime = name1 + System.currentTimeMillis();
        }
        System.out.println("Database Work" + keyoneTime);
        //keyname Imran Sunny1670684450926

        String value = name1+"__"+email1+"__"+phone_home1+"__"+phone_office1+"__";
        KeyValueDB kvDB = new KeyValueDB(this);
        kvDB.insertKeyValue(keyoneTime,value);
    }

    public void loadData(String key){

        KeyValueDB kvDB = new KeyValueDB(this);
        String vv = kvDB.getValueByKey(key);
        String values[] = vv.split("__");
        for(int i=0; i<values.length; i++){
            System.out.println(values[i]);
        }


    }


}