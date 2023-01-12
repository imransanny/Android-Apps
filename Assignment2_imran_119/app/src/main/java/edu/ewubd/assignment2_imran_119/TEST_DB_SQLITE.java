package edu.ewubd.assignment2_imran_119;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TEST_DB_SQLITE extends AppCompatActivity implements View.OnClickListener {


    private EditText nameeditText, ageEditText, genderExitText;
    private Button addButoon, display_all_Data;

    test myDatabaseHelper; //database tar ekta variabble


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        myDatabaseHelper = new test(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();//data read ba write korte parbo

        nameeditText = findViewById(R.id.name_edit_id);
        ageEditText = findViewById(R.id.age_edit_id);
        genderExitText = findViewById(R.id.Gender_edit_id);
        addButoon = findViewById(R.id.Add_data_id);
        addButoon.setOnClickListener(this);
        display_all_Data = findViewById(R.id.display_alldata_id);
        display_all_Data.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String name = nameeditText.getText().toString();
        String age = ageEditText.getText().toString();
        String gender = genderExitText.getText().toString();

        if (view.getId() == R.id.Add_data_id) {
            long rowid = myDatabaseHelper.insertData(name, age, gender);
            if (rowid == -1) {
                Toast.makeText(getApplicationContext(), " Unsuccess", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), "Row " + rowid + "is successfully inserted", Toast.LENGTH_LONG).show();
            }
        }


        if (view.getId() == R.id.display_alldata_id) {
            Cursor resultset = myDatabaseHelper.displayAllData();//my databasehelper a methon obej hiisabe rekhechi and resutset cursor jehoto cursor return korbe

            //result koto row ache
            if(resultset.getCount()==0){
                //jodi 0 thake tar mane database a kichu nai
                showData("Error", "No data found");
                return;
            }
            StringBuffer stringBuffer = new StringBuffer(); //buffer a sob data rekhe dibo

            while (resultset.moveToNext()){
                stringBuffer.append("ID :"+resultset.getString(0)+"\n\n");//id return korbe cause 0 number column a id ache
                stringBuffer.append("Name :"+resultset.getString(1)+"\n\n");
                stringBuffer.append("Age :"+resultset.getString(2)+"\n\n");
                stringBuffer.append("Gender :"+resultset.getString(3)+"\n\n");

            }
            showData("ResultSet", stringBuffer.toString());  //data display korar jnno

        }
    }
    public void showData(String title, String mess){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//data ta app er display te show korbe
        builder.setTitle(title);
        builder.setMessage(mess);
        builder.setCancelable(true);
        builder.show();
    }



}