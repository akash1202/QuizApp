package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHandler extends SQLiteOpenHelper {

    //all constants as they are static and final(Db=Database)
    //Db Version
    private static final int Db_Version=1;
    //Db Name
    private static final String Db_Name="users";
    //table name
    private static final String Table_Name="user";
    //Creating mycontacts Columns
    private static final String User_id="id";
    private static final String User_name="name";
    private static final String User_password="password";
    //constructor here
    public DbHandler(Context context)
    {
        super(context,Db_Name,null,Db_Version);
    }
    //creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
        // writing command for sqlite to create table with required columns
        String Create_Table="CREATE TABLE " + Table_Name + "(" + User_id
                + " INTEGER PRIMARY KEY," + User_name + " TEXT," + User_password + " TEXT" + ")";
        db.execSQL(Create_Table);
    }
    //Upgrading the Db
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        //create the table again
        onCreate(db);
    }
    //Add new User by calling this method
    public void addUser(User usr)
    {
        // getting db instance for writing the user
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
       //  cv.put(User_id,usr.getId());
        cv.put(User_name,usr.getName());
        cv.put(User_password,usr.getPassword());
        //inserting row
        Log.i("VRAJESHhere*******",""+ db.insert(Table_Name, null, cv));
        //close the database to avoid any leak
        db.close();
    }
    public boolean checkUser(User us)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM user WHERE name=? AND password=?",new String[]{us.getName(),us.getPassword()});
        Log.d("MyApp","cursor is here dbhandler****"+cursor.getCount());

        if(cursor.getCount()>0) {
            return true;
        }
        return false;
    }

}
