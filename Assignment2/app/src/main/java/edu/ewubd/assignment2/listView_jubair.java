package edu.ewubd.assignment2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class listView_jubair extends AppCompatActivity {
ListView mylistView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_jubair);

        mylistView = findViewById(R.id.listView);
       //intialize korlam My adapter
        MyAdapter myAdapter = new MyAdapter();
        mylistView.setAdapter(myAdapter);




    }
  //=====================================================
    private  class  MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {//koita item dekhate chai retun 0 mane 0 ta return joto toto
                              //bar ghorte 10 dile 10 bar getview method k calll korbe
            return 20;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
      @SuppressLint("MissingInflatedId")
        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = layoutInflater.inflate(R.layout.sigle_list_item_jubair,viewGroup,false);




          ImageView imageView = myView.findViewById(R.id.im_id);
            TextView textView = myView.findViewById(R.id.textView2);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"Hello\nItem number"+position,Toast.LENGTH_LONG).show();
                }
            });



            return myView;
        }
    }
    //===================================================================



}
