package edu.ewubd.assintmentsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayAllContact extends AppCompatActivity {

    EditText name, email, phone_home, phone_office;
    ImageView image;
    TextView photo, cancle, save;
    String keyoneTime = "";
    String saved = "";
    String  newString = "";
    int c=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_all_contact);

    name = findViewById(R.id.name_display_id);
    email =  findViewById(R.id.email_display_id);
    phone_home = findViewById(R.id.phone_home_display_id);
    phone_office = findViewById(R.id.phone_office_display_id);
    image = findViewById(R.id.image_view_id);


      Bundle extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");
        System.out.println("new"+newString);
saved = newString;


            loadData(newString);




    }

    public void loadData(String key){
        KeyValueDB kv = new KeyValueDB(this);
        String v = kv.getValueByKey(key);
        String []values= v.split("__");
        name.setText(values[0]);
        email.setText(values[1]);
        phone_home.setText(values[2]);
        phone_office.setText(values[3]);
        String s_image =values[4];
        byte[] bytes= Base64.decode(s_image,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        image.setImageBitmap(bitmap);
    }


}/*
    // Code for Decode button
        btnDecode.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // decode base64 string
            byte[] bytes= Base64.decode(sImage,Base64.DEFAULT);
            // Initialize bitmap
            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            // set bitmap on imageView
            imageView.setImageBitmap(bitmap);
        }
    });
}*/