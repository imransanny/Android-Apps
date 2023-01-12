package edu.ewubd.cse2020160119;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HttpRequest extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private void httpRequest(final String[] keys, String [] values) {
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
            for(int i=0; i<ja.length(); i++){
                JSONObject event = ja.getJSONObject(i);
                String eventKey = event.getString("e_key");
                String eventValue = event.getString("e_value");
                // split eventValue to show in event list
            }
        }else{
            System.out.println(jo);
        }
    }



}


