package edu.ewubd.assignment2_imran_119;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class test extends SQLiteOpenHelper {
    public static final String Database_Name = "test.db";
    public static final String Table_Name = "test_details";
    //column name
    public static final String ID = "_id"; //uderscore means primarykey
    public static final String NAME = "Name";
    public static final String AGE = "Age";
    public static final String GENDER = "Gender";

//create table
    private  static final String CREATE_TABLE = "CREATE TABLE "+Table_Name+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+AGE+" INTEGER,"+GENDER+" VARCHAR(15));";
    //DROP TABLE
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+Table_Name;
    private static final String SELECT_ALL = "SELECT * FROM "+Table_Name;
    public static final int Version_Number = 8; //jokhon change korbo tokhon eta oio change korte hobe
    private Context context;



public test(Context context){
    super(context,Database_Name,null, Version_Number);
    this.context = context;
}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    //Create Table
       try{
           Toast.makeText(context," onCreate is called " ,Toast.LENGTH_LONG).show();
           sqLiteDatabase.execSQL(CREATE_TABLE);
       }catch (Exception e){
           Toast.makeText(context," Exception " + e,Toast.LENGTH_LONG).show();
       }

    }

    //when Database upgrate hobe jodi Gender name arekta row create kori
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    //modify korle ager ta drop kore nuton kore create korte hobe

        try {
            Toast.makeText(context," onUpgrade is called " ,Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }catch (Exception e){

            Toast.makeText(context," Exception " + e,Toast.LENGTH_LONG).show();
        }

    }

    public  long insertData(String name, String age, String gender){
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gender);

        long rowid = sqLiteDatabase.insert(Table_Name,null,contentValues);
        return rowid;

    }

   public  Cursor displayAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.rawQuery(SELECT_ALL, null);//sob eksathe return korbe
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL,null);
        return cursor;
    }

}
