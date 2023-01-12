package edu.ewubd.assignment2;

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

                 if(c==1){
                     load(newString);
                     saved = newString;
                     c++;
                 }else{
                     load(saved);
                 }

          //imm1670705928567




    }

    public void load(String keyy){

        KeyValueDBB kv = new KeyValueDBB(this);

        String v = kv.getValueByKey(keyy);

        String []valu= v.split("____");

        for(int i=0; i<valu.length; i++){
            System.out.println(valu[0]);
            System.out.println(valu[1]);
            System.out.println(valu[2]);
        name.setText(valu[0]);
        email.setText(valu[1]);
        phone_home.setText(valu[2]);
        phone_office.setText(valu[3]);
        String s_image =valu[4];
        byte[] bytes= Base64.decode(s_image,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        image.setImageBitmap(bitmap);



    }}


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