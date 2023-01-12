package edu.ewubd.storeandretrievefirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private Context context;
    private List<Upload> uploadList;

    public MyAdapter(Context context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);;
        View view = layoutInflater.inflate(R.layout.item_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //data set korbo
        Upload upload = uploadList.get(i);
        myViewHolder.textView.setText(upload.getImageName());
        myViewHolder.image_des.setText(upload.getImagedes());

        Picasso.get()
                .load(upload.getImageUrl())
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(myViewHolder.imageView);


    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView, image_des;
        ImageView imageView;

        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
          textView =   itemView.findViewById(R.id.cardText_id);
          imageView = itemView.findViewById(R.id.card_image_id);
          image_des = itemView.findViewById(R.id.cardDescrip_id);

        }
    }



}
