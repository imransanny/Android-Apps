package edu.ewubd.cse2020160119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Event_details extends AppCompatActivity {
    TextView name,place,capacity,budget,email,phone,description,date,eventtype;
    Button back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        name = findViewById(R.id.tvEventName);
        place = findViewById(R.id.tvEventplace);
        capacity = findViewById(R.id.tvEventCapacity);
        budget = findViewById(R.id.tvEventBud);
        email = findViewById(R.id.tvEventEmail);
        phone = findViewById(R.id.tvEventPhone);
        description = findViewById(R.id.tvEventDes);
        date = findViewById(R.id.tvEventDateTime);
        eventtype = findViewById(R.id.tvEventtype);
back = findViewById(R.id.backbtn);
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(getApplicationContext(), UpomingEventActivity.class);
        startActivity(i);
        finish();
    }
});
Bundle b = getIntent().getExtras();
String pos_key = b.getString("NEWDBkey");
        System.out.println("NEWWW = "+pos_key);
        int pos_int = Integer.parseInt(pos_key) ;
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_p");
        rows.moveToPosition(pos_int+1);


        if (rows.getCount() == 0) {
            return;
        }
           //while (rows.moveToNext()) {

            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String[] fieldValues = eventData.split("___");

               if(fieldValues.length ==9) {
                   String name2 = fieldValues[0];
                   String place2 = fieldValues[1];
                   String capacity2 = fieldValues[2];
                   String budget2 = fieldValues[3];
                   String email2 = fieldValues[4];
                   String phone2 = fieldValues[5];
                   String description2 = fieldValues[6];
                   String date2 = fieldValues[7];
                   String eventType2 = fieldValues[8];
                   System.out.println("perfect"+email2+" "+phone2+""+eventType2);

                   name.setText(name2);
                   place.setText(place2);
                   capacity.setText(capacity2);
                   budget.setText(budget2);
                   email.setText(email2);
                   phone.setText(phone2);
                   description.setText(description2);
                   date.setText(date2);
                   eventtype.setText(eventType2);
               }else{
                   System.out.println("length not 9"+fieldValues.length);

               }




    }
}
