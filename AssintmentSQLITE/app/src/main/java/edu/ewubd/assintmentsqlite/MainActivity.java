package edu.ewubd.assintmentsqlite.;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText name, email, phone_home, phone_office;
    ImageView imageView;
    TextView photo, cancle, save;
    String keyoneTime = "";
    String Val = "";
    String sImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name_text_id);
        email = findViewById(R.id.email_text_id);
        phone_home = findViewById(R.id.phone_home_text_id);
        phone_office = findViewById(R.id.office_text_id);
        imageView = findViewById(R.id.image_id);
        photo = findViewById(R.id.photo_id);
        cancle = findViewById(R.id.cancle_id);
        save = findViewById(R.id.save_id);


        cancle.setOnClickListener(v -> cancl());
        save.setOnClickListener(v ->saveall());


        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this
                            , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);

                }
                else
                {
                    selectImage();
                }

            }

        });

    }

    private void selectImage() {
        imageView.setImageBitmap(null);
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select Image"),100);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==100 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {

            selectImage();
        }
        else
        {

            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100 && resultCode==RESULT_OK && data!=null)
        {

            Uri uri=data.getData();
            Uri  ui = data.getData();
            if(uri == ui) {
                imageView.setImageURI(uri);
               photo.setVisibility(View.GONE);
            }

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] bytes=stream.toByteArray();
                sImage= Base64.encodeToString(bytes,Base64.DEFAULT);
               // System.out.println("SIMAGE = "+sImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






    public void cancl(){
       //loadData("Imran Sunny1670684450926");
        MainActivity m = new MainActivity();
        Intent i = new Intent(MainActivity.this, DisplayAllContact.class);
        i.putExtra("STRING_I_NEED", Val);
        startActivity(i);

    }

    public void saveall(){
        String name1 = name.getText().toString();
        String email1 = email.getText().toString();
        String phone_home1 = phone_home.getText().toString();
        String phone_office1 = phone_office.getText().toString();
        String bitmap_encode_image = sImage;


        if (name.length() ==0){
            name.setError("Enter Name");
        }//else if(name.length()<=2){
           //Toast.makeText(this,"name must be 4 character " ,Toast.LENGTH_LONG).show();
      //  }

        if(email.length() ==0){
            email.setError("Enter Email");
        }//else if(email.length()<8){
           // Toast.makeText(this,"Enter valid emial Address " ,Toast.LENGTH_LONG).show();
     //   }

        if(phone_home.length() ==0){
            phone_home.setError("Enter home phone number");
        }//else if(phone_home.length()<10){
           // Toast.makeText(this,"Enter valid phone number " ,Toast.LENGTH_LONG).show();
       // }

        if(phone_office.length() ==0){
            phone_office.setError("Enter office phone number");
        }//else if(phone_office.length()<8){
           // Toast.makeText(this,"Enter valid phone Address " ,Toast.LENGTH_LONG).show();
       // }

       if((name.length() ==0) ||(email.length()==0) ||(phone_home.length() ==0) || (phone_office.length() ==0)) {
         Toast.makeText(this," save uncessfull fillup the Empty field " ,Toast.LENGTH_LONG).show();
        } else{


        try{
            Toast.makeText(this," Save Successfully  " ,Toast.LENGTH_LONG).show();
    if (keyoneTime.length() ==0){
            keyoneTime = name1 + System.currentTimeMillis();
        }
        Val = keyoneTime;
        System.out.println("Database Work" + keyoneTime);
        //keyname Imran Sunny1670690575144

        String value = name1+"__"+email1+"__"+phone_home1+"__"+phone_office1+"__"+bitmap_encode_image+"__";
       KeyValueDB kvDB = new KeyValueDB(this);
       kvDB.insertKeyValue(keyoneTime,value);

        }catch (Exception e){
            Toast.makeText(this," Something is Wrong! " + e,Toast.LENGTH_LONG).show();
        }

       }

    }

public void loadData(String key){

    KeyValueDB kvDB = new KeyValueDB(this);
    String vv = kvDB.getValueByKey(key);
    String values[] = vv.split("__");
    for(int i=0; i<values.length; i++){
       // System.out.println(values[i]);
    }


}


}