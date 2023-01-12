package edu.ewubd.cse2020160119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class event_information_lab3 extends AppCompatActivity{

    EditText name,place,description,capacity,budget,dateandtime,Email,Phone;
    Button cancle,share,save;
    RadioButton indoor, outdoor, online;
    SharedPreferences sharedPreferences;
    String KeyOneTime  ="";
    private String key = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_information_lab3);



        dateandtime = findViewById(R.id.date_time_id);
      //  dateandtime.setOnClickListener(v->funcSave());
        description = findViewById(R.id.description_id);
        //description.setOnClickListener(v->funcSave());
        capacity = findViewById(R.id.capacity_id);
        //capacity.setOnClickListener(v->funcSave());
        budget = findViewById(R.id.budget_id);
        //budget.setOnClickListener(v->funcSave());
        online = findViewById(R.id.online_id);
        //online.setOnClickListener(v->funcSave());

        name = findViewById(R.id.name_id);
       // name.setOnClickListener(v->funcSave());
        // sharedPreferences = this.getSharedPreferences("Store_Data_myPref", MODE_PRIVATE);

        //   String s1 = sharedPreferences.getString("name1", "HI");
        //  name.setText(s1);


        Email = findViewById(R.id.Email_id);
      //  Email.setOnClickListener(v->funcSave());
        Phone = findViewById(R.id.phone_id);
        //Phone.setOnClickListener(v->funcSave());


        place = findViewById(R.id.place_id);
        place.setOnClickListener(v->funcSave());

        indoor = findViewById(R.id.indoor_id);
       // indoor.setOnClickListener(v->funcSave());

        outdoor = findViewById(R.id.outdoor_id);
       // outdoor.setOnClickListener(v->funcSave());

        online = findViewById(R.id.outdoor_id);
      //  online.setOnClickListener(view->funcSave());


        save = findViewById(R.id.save_button);
        save.setOnClickListener(v->funcSave());
        // save.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //  public void onClick(View view) {
        //   Intent i = new Intent(event_information_lab3.this, Home.class);
        // startActivity(i);
        //  }
        //  });

        share = findViewById(R.id.share_button);
        share.setOnClickListener(v->sharebtn());

        cancle = findViewById(R.id.cancle_Button);
       cancle.setOnClickListener(v -> finish());

/*
        sharedPreferences = this.getSharedPreferences("Store_Data_myPref", MODE_PRIVATE);
        SharedPreferences.Editor e = sharedPreferences.edit();
        String name1 = name.getText().toString();
        e.putString("name1", "imran");
        e.apply();

        sharedPreferences = this.getSharedPreferences("Store_Data_myPref", MODE_PRIVATE);
        String s22 = sharedPreferences.getString("REMEMBER PASSWORD", "");
        s22 = "nOOO";

*/


       // loadData("imran1671069618833");

        Intent i = getIntent();
        if(i.hasExtra("EventKey")){
            key = i.getStringExtra("EventKey");
        }


    }


    public void funcSave(){
       /* System.out.println(name.getText().toString().trim());
        System.out.println(place.getText().toString().trim());
        System.out.println(dateandtime.getText().toString().trim());
        System.out.println(capacity.getText().toString().trim());
        System.out.println(budget.getText().toString().trim());
        System.out.println(Email.getText().toString().trim());
        System.out.println(Phone.getText().toString().trim());
        System.out.println(description.getText().toString().trim());

        if(indoor.isChecked()){
            System.out.println("Indoor Clicked");
        }
        else if(outdoor.isChecked()){
            System.out.println(("Outdoor Checked"));
        }else if(online.isChecked()){
            System.out.println("Online Checked");
        }*/
        String name1 =name.getText().toString();
        String place1 =place.getText().toString();
        String capacity1 = capacity.getText().toString();
        String budget1 = budget.getText().toString();
        String email1 =Email.getText().toString();
        String phone1 =Phone.getText().toString();
        String description1 =description.getText().toString();
        String date1 = dateandtime.getText().toString();

        String type1 = "";
        if(indoor.isChecked()){
            type1 = "indor";
        }else if(outdoor.isChecked()){
            type1= "outdoor";
        }else if(online.isChecked()){
            type1 = "online";
        }

     //if(KeyOneTime.length()==0){
            KeyOneTime  =  name1 + System.currentTimeMillis();

       // }

        //imran1669279402612
        System.out.println("Database work " + KeyOneTime);


        //
        String value = name1 + "___"+place1+"___"+capacity1+"___"+budget1+"___"+email1+"___"+phone1+"___"+description1+"___"+date1+"___"+type1+"___";
        KeyValueDB kvdb = new KeyValueDB(this);
        kvdb.insertKeyValue(KeyOneTime,value);


        String[] keys = {"action", "id", "semester", "key", "event"};
        String[] values = {"backup", "2020-1-60-119", "2022-3", KeyOneTime, value};
        httpRequest(keys, values);



        // System.out.println("Database value " + value);
     Toast.makeText(getApplicationContext(),"Save Successfully", Toast.LENGTH_LONG).show();

    }

    @SuppressLint("StaticFieldLeak")
    private void httpRequest(final String[] keys, String [] values) {
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void...param) {
               try{
                   List<NameValuePair>params = new ArrayList<NameValuePair>();
                   for(int i=0; i<keys.length; i++){
                       params.add(new BasicNameValuePair(keys[i], values[i]));
                   }
                   String data = JSONParser.getInstance().makeHttpRequest("https://muthosoft.com/univ/cse489/index.php", "POST", params);
                   return data;
               }catch (Exception ex){
                   ex.printStackTrace();
               }
               return  null;
            }
protected void onPostExecute(String data){
                if(data != null){
                    try {
                        updateEventListByServerData(data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
}

        }.execute();

    }


    public void updateEventListByServerData(String data) throws JSONException {
        JSONObject jo = new JSONObject(data);
        if(jo.has("events")){
            JSONArray ja = jo.getJSONArray("events");
            for(int i=0; i<ja.length(); i++){
                JSONObject event = ja.getJSONObject(i);
                String eventKey = event.getString("key");
                String eventValue = event.getString("value");
                // split eventValue to show in event list
            }
        }else{
            System.out.println(jo);
        }
    }



    public void loadData(String key){
        KeyValueDB kv = new KeyValueDB(this);
        String v = kv.getValueByKey(key);
        String[] values = v.split("___");
        for(int i=0; i<values.length; i++){
            System.out.println(values[i]);
        }

    }


    public  void sharebtn(){

        Intent i = new Intent( event_information_lab3.this, UpomingEventActivity.class);
        startActivity(i);
        System.out.println("Share Button clicked");
    }


}//imran1671091796503