package edu.ewubd.storeandretrievefirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.time.Instant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button choosebtn, savebtn, displaybtn;
    private ImageView imageView;
    private EditText imageNameEditText,imagedesEdittext;
    private ProgressBar progressBar;
    private Uri imageUri;
DatabaseReference databaseReference;
StorageReference storageReference;

StorageTask uploadTask;

    private static  final int IMAGE_REQUEST = 1; //jokhon image select korbo request 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Create a Cloud Storage reference from the app
      //  StorageReference storageRef = storage.getReference();

        databaseReference = FirebaseDatabase.getInstance().getReference("Upload");
        storageReference = FirebaseStorage.getInstance().getReference("Upload");


        choosebtn = findViewById(R.id.chose_image_btn);
        savebtn = findViewById(R.id.save_btn_idd);
        displaybtn = findViewById(R.id.display_btn_idd);
        progressBar = findViewById(R.id.progreess_id);

        imageNameEditText = findViewById(R.id.editText_image_btn);
        imageView = findViewById(R.id.image_ide);
        imagedesEdittext = findViewById(R.id.editText_image_des);


savebtn.setOnClickListener(this);
choosebtn.setOnClickListener(this);
displaybtn.setOnClickListener(this);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
switch (view.getId()){
    case R.id.chose_image_btn:
        openFileChoser();

        break;
    case R.id.save_btn_idd:
         if(uploadTask!=null && uploadTask.isInProgress()){
             Toast.makeText(getApplicationContext(),"Uploading in progress ", Toast.LENGTH_LONG).show();

         }else{
             saveDAta();
         }

        break;
    case R.id.display_btn_idd:
        Intent intent = new Intent(MainActivity.this, Image_display.class);
        startActivity(intent);
        break;
}


    }

    //image extention find korte====================
    public String getFileExtension(Uri imageUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }



    private void saveDAta() {

String imageName = imageNameEditText.getText().toString().trim();
String imagedes = imagedesEdittext.getText().toString().trim();

if(imageName.isEmpty()){
    imageNameEditText.setError("Enter the image name");
    imageNameEditText.requestFocus();
    return;
}


StorageReference ref = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));

ref.putFile(imageUri)
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
             //jokhon sucessfull upload hobe
                Toast.makeText(getApplicationContext(),"Image is store sucessfully", Toast.LENGTH_LONG).show();

                Task<Uri> urlTask = taskSnapshot.getStorage()
                        .getDownloadUrl();
                while (!urlTask.isSuccessful());
                 Uri downloadUri = urlTask.getResult();



                //store hole tar ekta link database a store kore rakhte chaile
                Upload upload = new Upload(imageName,downloadUri.toString(),imagedes);
                String uploadID = databaseReference.push().getKey();
                databaseReference.child(uploadID).setValue(upload);

            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
   //jodi store na hoi
                Toast.makeText(getApplicationContext(),"Image is not store ", Toast.LENGTH_LONG).show();
            }
        });





    }

    private void openFileChoser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
           // Picasso.get(this).load(imageUri);
            Picasso.get().load(imageUri).into(imageView);
        }
    }
}