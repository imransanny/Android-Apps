package edu.ewubd.cse2020160119;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpomingEventActivity extends AppCompatActivity {

    Button exit, create_new, home;
    ListView lvenenvts;
    ArrayList<Event> events;
    CustomEventAdapter adapter;




    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview);
        create_new =findViewById(R.id.create_new_id);
        exit = findViewById(R.id.exit_id);
        home = findViewById(R.id.history_id);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }
        });
        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpomingEventActivity.this, event_information_lab3.class);
                startActivity(i);
            }
        });

        //exit
        //create_new = findViewById(R.)


// initialize list-reference by ListView object defined in XML
        lvenenvts = findViewById(R.id.lvevent_id);
// load events from database if there is any

      //  loadDataserver();
        loadData();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), event_information_lab3.class);
                i.putExtra("Exit", "Yes");
                startActivity(i);
                        finish();

            }
        });

    }
/*
    private void loadDataserver() {

        String[] keys = {"action", "id", "semester"};
        String[] values = {"restore", "2020-1-60-119","2022-3"};

        httpRequest(keys,values);
    }

    @SuppressLint("StaticFieldLeak")
    private void httpRequest(String[] keys, String[] values) {
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void...param) {
                try{
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
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
            for(int i=0; i<ja.length(); i++) {
                JSONObject event = ja.getJSONObject(i);
                String eventKey = event.getString("key");
                String eventValue = event.getString("value");

                // split eventValue to show in event list
                System.out.println("GET key server="+eventKey );
                System.out.println("GET from server="+eventValue );
                String[] fieldValues = eventValue.split("___");


                events = new ArrayList<>();

                if (fieldValues.length == 9) {
                    String name = fieldValues[0];
                    String place = fieldValues[1];
                    String capacity = fieldValues[2];
                    String budget = fieldValues[3];
                    String email = fieldValues[4];
                    String phone = fieldValues[5];
                    String description = fieldValues[6];
                    String date = fieldValues[7];
                    String eventType = fieldValues[8];

                    //problem
                    Event e = new Event(eventKey, name, place, capacity, budget, email, phone, description, date, eventType);
                    events.add(e);
                } else {
                    System.out.println(fieldValues.length);
                }

                adapter = new CustomEventAdapter(this, events);
                lvenenvts.setAdapter(adapter);
                // handle the click on an event-list item
                lvenenvts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                        // String item = (String) parent.getItemAtPosition(position);
                        System.out.println(position);
                        Intent i = new Intent(UpomingEventActivity.this, event_information_lab3.class);
                        i.putExtra("EventKey", events.get(position).key);
                        startActivity(i);
                    }
                });
                // handle the long-click on an event-list item
                lvenenvts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        //String message = "Do you want to delete event - "+events[position].name +" ?";
                        String message = "Do you want to delete event - " + events.get(position).name + " ?";
                        System.out.println(message);
                        //showDialog(message, "Delete Event", events.get(position).key);
                        return true;
                    }
                });


                lvenenvts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String message = "Do yoou Want to delete event-"+events.get(position).name+"?";
                        showDialog(message,"Delete Event",events.get(position).key);


                        return true;
                    }
                });


            }
        }else{
            System.out.println(jo);
        }
    }


//remote database

    private void showDialog(String message, String title, String key) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
               // KeyValueDB db = new KeyValueDB(getApplicationContext());
             //   db.deleteDataByKey(key);
                dialog.cancel();
                //loadData();//efficiant korte eta na kore ArryList a delete korte hobe key tak khuje
                loadDataserver();
                adapter.notifyDataSetChanged();

            }

        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

*/

    //=========================sqlite database
    private void loadData(){

        events = new ArrayList<>();
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_p");

        if (rows.getCount() == 0) {
            return;
        }
        //events = new Event[rows.getCount()];

       // String s1 = sp.getString("REMEMBER_USERID", "");
        while (rows.moveToNext()) {

            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String[] fieldValues = eventData.split("___");
    if(fieldValues.length ==9) {
        String name = fieldValues[0];
        String place = fieldValues[1];
        String capacity = fieldValues[2];
        String budget = fieldValues[3];
        String email = fieldValues[4];
        String phone = fieldValues[5];
        String description = fieldValues[6];
        String date = fieldValues[7];
        String eventType = fieldValues[8];

        //problem
        Event e = new Event(key, name, place, capacity, budget, email, phone, description, date, eventType);
        events.add(e);
        System.out.println("print for loop");

    }else{
        System.out.println(fieldValues.length);
    }

        }
        db.close();



        adapter = new CustomEventAdapter(this, events);

        lvenenvts.setAdapter(adapter);

        // handle the click on an event-list item
        lvenenvts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                // String item = (String) parent.getItemAtPosition(position);
                System.out.println("pos key"+position);
                String pos_k = String.valueOf(position);


                Intent i = new Intent(UpomingEventActivity.this, Event_details.class);
                i.putExtra("EventKey", events.get(position).key);
                i.putExtra("NEWDBkey",pos_k);
                startActivity(i);
            }
        });
        // handle the long-click on an event-list item
        lvenenvts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //String message = "Do you want to delete event - "+events[position].name +" ?";
                String message = "Do you want to delete event - "+events.get(position).name +" ?";
                System.out.println(message);
                //showDialog(message, "Delete Event", events.get(position).key);
                return true;
            }
        });

        lvenenvts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
               String message = "Do yoou Want to delete event-"+events.get(position).name+"?";
               showDialogdb(message,"Delete Event",events.get(position).key);
               
               
                return true;
            }
        });

    }





    private void showDialogdb(String message, String title, String key) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                KeyValueDB db = new KeyValueDB(getApplicationContext());
                 db.deleteDataByKey(key);
                 dialog.cancel();
                 loadData();//efficiant korte eta na kore ArryList a delete korte hobe key tak khuje
              // loadDataserver();
                 adapter.notifyDataSetChanged();

            }

        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
