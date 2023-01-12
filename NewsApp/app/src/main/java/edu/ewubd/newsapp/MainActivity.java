package edu.ewubd.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

   // GridView listView;
    ListView listView;
    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.gridview_all);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView_id);


        createTable();


        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }
    //====================================================
    //====================================================

    // ====================================================
    //====================================================

    private class  MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {

            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        @SuppressLint("MissingInflatedId")
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myvview = inflater.inflate(R.layout.item, viewGroup,false);

            ImageView itemcover= myvview.findViewById(R.id.image_view_1);
            TextView itemcat = myvview.findViewById(R.id.tvCAt);
            TextView itemtitle = myvview.findViewById(R.id.Item_title);
            TextView itemdes= myvview.findViewById(R.id.tvitemdes);
            LinearLayout layitem = myvview.findViewById(R.id.lay_item);


            HashMap<String, String> hashMap = arrayList.get(position);
            String cat = hashMap.get("cat"); //key diye khuje niye ashbo hashmap theke
            String imageurl = hashMap.get("image");
            String title = hashMap.get("title");
            String description = hashMap.get("des");


            Picasso.get().load(imageurl)
                    .placeholder(R.drawable.discount_stratergy)//jokhon ager pic ta load na hobe ager ta dekhabe
                    .into(itemcover);//item cover er moddhe chobita load hobe



            itemcat.setText(cat);
            itemtitle.setText(title);
            itemdes.setText(description);

//ramdom color dibe TECH lekha ta
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            itemcat.setBackgroundColor(color);




layitem.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        News_Details.TITLE = title;
        News_Details.DESCRIPTION = description;

        Bitmap bitmap = ((BitmapDrawable)itemcover.getDrawable()).getBitmap();

        News_Details.mybitmap = bitmap;


        startActivity(new Intent(MainActivity.this,News_Details.class));
    }
});


            return myvview;
        }
    }


    //====================================================
    // ====================================================
    // ====================================================
    private void createTable(){
        hashMap = new HashMap<>();
        hashMap.put("cat", "TECH");
        hashMap.put("image", "https://images.prothomalo.com/prothomalo-bangla%2F2022-12%2F1bc9fffa-13f9-4f55-87d9-cd18da9887e7%2FFRA_DNK_REPORT.jpg?rect=0%2C0%2C2100%2C1181&auto=format%2Ccompress&fmt=webp&format=webp&w=900&dpr=1.0.jpg");
        hashMap.put("title", "মেসির চোখে এমবাপ্পে, এমবাপ্পের চোখে মেসি");
        hashMap.put("des", "আর মাত্র একটা ম্যাচ। সে ম্যাচের বাধা ফ্রান্স–আর্জেন্টিনার যারাই পেরোবে, তারাই হবে বিশ্ব চ্যাম্পিয়ন। শিরোপার জয়ের লক্ষ্যে আর্জেন্টিনার বড় ভরসার নাম লিওনেল মেসি এবং ফ্রান্সের আস্থা থাকবে এমবাপ্পের ওপর। মেসি–এমবাপ্পের দুজন আছেনও দুর্দান্ত ছন্দে।");
    arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("cat", "country");
        hashMap.put("image", "https://images.prothomalo.com/prothomalo-bangla%2F2022-12%2F1bc9fffa-13f9-4f55-87d9-cd18da9887e7%2FFRA_DNK_REPORT.jpg?rect=0%2C0%2C2100%2C1181&auto=format%2Ccompress&fmt=webp&format=webp&w=900&dpr=1.0.jpg");
        hashMap.put("title", "country\tআন্তমহাদেশীয় ক্ষেপণাস্ত্র মোতায়েন রাশিয়ার\n" +
                "\n");
        hashMap.put("des", "ইউক্রেনের জন্য অত্যাধুনিক প্যাট্রিয়ট আকাশ প্রতিরক্ষাব্যবস্থা পাঠাতে পরিকল্পনা চূড়ান্ত করছে যুক্তরাষ্ট্র। এতে ক্ষুব্ধ প্রতিক্রিয়া জানিয়েছে রাশিয়া। যুক্তরাষ্ট্রের পরিকল্পনা জানার পর রাশিয়ার পক্ষ থেকে আন্তমহাদেশীয় ক্ষেপণাস্ত্র মোতায়েন করার ঘোষণা দেওয়া হয়েছে।");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("cat", "plitics");
        hashMap.put("image", "https://images.prothomalo.com/prothomalo-bangla%2F2022-12%2F1bc9fffa-13f9-4f55-87d9-cd18da9887e7%2FFRA_DNK_REPORT.jpg?rect=0%2C0%2C2100%2C1181&auto=format%2Ccompress&fmt=webp&format=webp&w=900&dpr=1.0.jpg");
        hashMap.put("title", "যশোরে বিএনপি নেতাকে তুলে নেওয়ার অভিযোগ\n" +
                "\n");
        hashMap.put("des", "যশোর জেলা বিএনপির আহ্বায়ক কমিটির সদস্য মিজানুর রহমান খানকে গোয়েন্দা পুলিশ তুলে নিয়ে গেছে বলে অভিযোগ পাওয়া গেছে। আজ বৃহস্পতিবার দুপুরে যশোর উপশহর এলাকা থেকে তাঁকে তুলে নেওয়া হয়েছে বলে বিএনপি নেতারা দাবি করেন। মিজানুর রহমান জেলা বিএনপির সাবেক যুগ্ম সম্পাদক ও যশোর চেম্বার অব কমার্সের সাবেক সভাপতি। এবারও চেম্বার নির্বাচনের ");
        arrayList.add(hashMap);


        hashMap = new HashMap<>();
        hashMap.put("cat", "plitics1");
        hashMap.put("image", "https://images.prothomalo.com/prothomalo-bangla%2F2022-12%2F1bc9fffa-13f9-4f55-87d9-cd18da9887e7%2FFRA_DNK_REPORT.jpg?rect=0%2C0%2C2100%2C1181&auto=format%2Ccompress&fmt=webp&format=webp&w=900&dpr=1.0.jpg");
        hashMap.put("title", "ম৯ বছর পর মুরাদনগর উপজেলা আ.লীগের সম্মেলন আজ, উৎসাহ-উদ্দীপনা");
        hashMap.put("des", "দীর্ঘ ৯ বছর পর কুমিল্লার মুরাদনগর উপজেলা আওয়ামী লীগের ত্রিবার্ষিক সম্মেলন অনুষ্ঠিত হতে যাচ্ছে। আজ বৃহস্পতিবার সকালে মুরাদনগর ডি আর সরকারি উচ্চবিদ্যালয় মাঠে এ সম্মেলন অনুষ্ঠিত হবে। \n" +
                "সম্মেলন ঘিরে ইতিমধ্যে নেতা-কর্মীদের মধ্যে উৎসাহ-উদ্দীপনা বিরাজ করছে। এ উপলক্ষে ব্যাপক প্রস্তুতি নেওয়া হয়েছে।\n" +
                "\n");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("cat", "plitics1");
        hashMap.put("image", "https://images.prothomalo.com/prothomalo-bangla%2F2022-12%2F1bc9fffa-13f9-4f55-87d9-cd18da9887e7%2FFRA_DNK_REPORT.jpg?rect=0%2C0%2C2100%2C1181&auto=format%2Ccompress&fmt=webp&format=webp&w=900&dpr=1.0.jpg");
        hashMap.put("title", "আন্তমহাদেশীয় ক্ষেপণাস্ত্র মোতায়েন রাশিয়ার");
        hashMap.put("des", "ইউক্রেনের জন্য অত্যাধুনিক প্যাট্রিয়ট আকাশ প্রতিরক্ষাব্যবস্থা পাঠাতে পরিকল্পনা চূড়ান্ত করছে যুক্তরাষ্ট্র। এতে ক্ষুব্ধ প্রতিক্রিয়া জানিয়েছে রাশিয়া। যুক্তরাষ্ট্রের পরিকল্পনা জানার পর রাশিয়ার পক্ষ থেকে আন্তমহাদেশীয় ক্ষেপণাস্ত্র মোতায়েন করার ঘোষণা দেওয়া হয়েছে।" +
                "\n");
        arrayList.add(hashMap);






    }





}