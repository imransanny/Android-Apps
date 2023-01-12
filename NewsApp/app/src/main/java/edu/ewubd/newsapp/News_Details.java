package edu.ewubd.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class News_Details extends AppCompatActivity {

    ImageView cover_image;
    TextView tvdes, Tvtitle;
    FloatingActionButton FabButton;
    TextToSpeech textToSpeech;

    public static  String TITLE = "";  //main activity theke ashbe aikhen
    public static String DESCRIPTION = "";
    public static Bitmap mybitmap = null; //image k store kore rakhbo



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        cover_image = findViewById(R.id.coverimg_newsdetais);
        tvdes = findViewById(R.id.Tv_desctiop);
        Tvtitle = findViewById(R.id.Tv_title);
        FabButton = findViewById(R.id.floatAction_buttion_id);


        Tvtitle.setText(TITLE);
        tvdes.setText(DESCRIPTION);

        if ((mybitmap != null)) cover_image.setImageBitmap(mybitmap);
       textToSpeech = new TextToSpeech(News_Details.this, new TextToSpeech.OnInitListener(){

           @Override
           public void onInit(int i) {

           }
       });

      FabButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String text = tvdes.getText().toString();
              textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);

          }
      });


    }
}