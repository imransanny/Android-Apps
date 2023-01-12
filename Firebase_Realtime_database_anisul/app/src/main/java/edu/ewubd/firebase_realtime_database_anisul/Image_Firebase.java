package edu.ewubd.firebase_realtime_database_anisul;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

public class Image_Firebase extends AppCompatActivity implements View.OnClickListener {

    Button chooseimage, savebtn, displaybtn;
    ImageView image;
    EditText editText;
    ProgressBar progressBar;
    private Uri imageurl;
    private static final int IMAGE_REQUEST = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_firebase);

        chooseimage = findViewById(R.id.choseimage);
        savebtn = findViewById(R.id.save_img);
        displaybtn = findViewById(R.id.displayimg);
        image = findViewById(R.id.image_ima);
        editText = findViewById(R.id.edittex_iamge);
        progressBar = findViewById(R.id.progress_image);

chooseimage.setOnClickListener(this);
savebtn.setOnClickListener(this);
displaybtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.choseimage://file chooser

                openfileChooser();

                break;
            case R.id.save_img:
                break;
            case R.id.displayimg:
                break;

        }
    }

     void openfileChooser() {
         Intent i = new Intent();
         i.setType("image/*");
         i.setAction(Intent.ACTION_GET_CONTENT);
         startActivityForResult(i,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && requestCode == RESULT_OK && data!=null && data.getData()!= null){//uri pabo
            imageurl = data.getData();
            Picasso.with(this).load(imageurl).into(image);
        }

    }
}