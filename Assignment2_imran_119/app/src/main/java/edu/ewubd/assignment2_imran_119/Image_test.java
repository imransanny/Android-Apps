package edu.ewubd.assignment2_imran_119;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.spec.ECField;

public class Image_test extends AppCompatActivity {

    Button encode1, decode1;
    ImageView imageView1;
    TextView textView;
    String sImage;

    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.image_test);

        encode1 = findViewById(R.id.button1);
        decode1 = findViewById(R.id.button2);
        imageView1 = findViewById(R.id.imageView2);


        encode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(Image_test.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                    ActivityCompat.requestPermissions(Image_test.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                } else {
                    selectImagee();
                }

            }


        });

    decode1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
              //decode
            byte[] bytes = Base64.decode(sImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            imageView1.setImageBitmap(bitmap);
        }
    });
    }
    private  void selectImagee(){
        textView.setText("");
        imageView1.setImageBitmap(null);
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Image"),100);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            selectImagee();
        }else{
            Toast.makeText(getApplicationContext(),"Permission Denied.", Toast.LENGTH_LONG).show();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==100 && resultCode==RESULT_OK && data!= null){
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] bytes = stream.toByteArray();
                //Base 64
                sImage = Base64.encodeToString(bytes,Base64.DEFAULT);
                textView.setText(sImage);


            }catch (IOException e){

            }

        }
    }
}



