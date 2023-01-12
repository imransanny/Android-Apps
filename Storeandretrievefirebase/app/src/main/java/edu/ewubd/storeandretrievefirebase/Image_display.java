package edu.ewubd.storeandretrievefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Image_display extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<Upload> uploadList;
    private ProgressBar progressBar;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);


        recyclerView = findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.prog_id);

        uploadList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Upload");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//database ttheke data anbo

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Upload upload = dataSnapshot1.getValue(Upload.class);
                    uploadList.add(upload);
                }


                myAdapter = new MyAdapter(Image_display.this, uploadList);
                recyclerView.setAdapter(myAdapter);




                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error : "+error.getMessage(),Toast.LENGTH_LONG).show();
           progressBar.setVisibility(View.INVISIBLE);
            }
        });



    }
}