package edu.ewubd.calculatordesign;

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
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.ByteArrayOutputStream;
        import java.io.IOException;

public class BitMAPSQL extends AppCompatActivity {

    // Initialize variable
    Button btnEncode,btnDecode;
    TextView textView;
    ImageView imageView;
    String sImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bitmapsql);

        btnEncode=findViewById(R.id.btn_encode);
        btnDecode=findViewById(R.id.btn_decode);
        textView=findViewById(R.id.textView);
        imageView= findViewById(R.id.imageView);

        // Code for Encode button
        btnEncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check condition
                if (ContextCompat.checkSelfPermission(BitMAPSQL.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    // when permission is nor granted
                    // request permission
                    ActivityCompat.requestPermissions(BitMAPSQL.this
                            , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);

                }
                else
                {
                    // when permission
                    // is granted
                    // create method
                    selectImage();
                }
            }

        });

        // Code for Decode button
        btnDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // decode base64 string
                byte[] bytes=Base64.decode(sImage,Base64.DEFAULT);
                // Initialize bitmap
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                // set bitmap on imageView
                imageView.setImageBitmap(bitmap);
            }
        });
    }
    private void selectImage() {
        // clear previous data
        textView.setText("");
        imageView.setImageBitmap(null);
        // Initialize intent
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        // set type
        intent.setType("image/*");
        // start activity result
        startActivityForResult(Intent.createChooser(intent,"Select Image"),100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // check condition
        if (requestCode==100 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            // when permission
            // is granted
            // call method
            selectImage();
        }
        else
        {
            // when permission is denied
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check condition
        if (requestCode==100 && resultCode==RESULT_OK && data!=null)
        {
            // when result is ok
            // initialize uri
            Uri uri=data.getData();
            // Initialize bitmap
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                // initialize byte stream
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                // compress Bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                // Initialize byte array
                byte[] bytes=stream.toByteArray();
                // get base64 encoded string
                sImage= Base64.encodeToString(bytes,Base64.DEFAULT);
                // set encoded text on textview
                textView.setText(sImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
