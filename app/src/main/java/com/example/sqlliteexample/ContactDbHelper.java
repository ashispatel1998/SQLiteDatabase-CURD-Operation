package com.example.sqlliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

//Using SQLiteOpenHelper Class we can create the database and table

public class ContactDbHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME="contact_db";
    //DATABASE VERSION
    public static final int DATABASE_VERSION=1;

    //Sql lite query for table creation
    public static final String CREATE_TABLE="create table "+ContactContract.ContactEntry.
            TABLE_NAME+"("+ContactContract.ContactEntry.CONTACT_ID+" number,"+ContactContract.ContactEntry.NAME+" text,"+
            ContactContract.ContactEntry.EMAIL+" text);";

    //Sql lite query for drop table is exists
    public static final String DROP_TABLE="drop table if exists "+ ContactContract.ContactEntry.TABLE_NAME+";";


    public ContactDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d("DATABASE OPERATIONS","Database Created");
    }

    // Create a Table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
       // Log.d("DATABASE OPERATIONS","Table Created");
    }

    //Upgrade the existing table
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Droping the table if exists
        sqLiteDatabase.execSQL(DROP_TABLE);
        //After droping the table it call onCreate() method to create the table
        onCreate(sqLiteDatabase);

    }

    // Creating a method for adding information to table
    public void addContact(int id,String name,String email,SQLiteDatabase database){

        // create an object of contentvalues to add information to the database
        ContentValues contentValues=new ContentValues();

        contentValues.put(ContactContract.ContactEntry.CONTACT_ID,id);
        contentValues.put(ContactContract.ContactEntry.NAME,name);
        contentValues.put(ContactContract.ContactEntry.EMAIL,email);

        // Add the information to the table usind SQLiteDatabase object
        database.insert(ContactContract.ContactEntry.TABLE_NAME,null,contentValues);
        Log.d("DATABASE OPERATIONS","1 row is inserted !");

    }

    // Read infomation from SQLite Database
    public Cursor readContacts(SQLiteDatabase database){

        //Columns to be read
        String[] projections={
                ContactContract.ContactEntry.CONTACT_ID,
                ContactContract.ContactEntry.NAME,
                ContactContract.ContactEntry.EMAIL
        };
        // (Table name , columns to be selected , condition to select ,selection arguments, group by clause , having clause , order by)
        Cursor cursor=database.query(ContactContract.ContactEntry.TABLE_NAME,
                projections,null,null,null,null,null);
        // Now we are having all the information in cursur object
        //Now we have to return the cursur
        return cursor;
    }

    //Database Updation
    public void updateContact(int id,String name,String email,SQLiteDatabase database){
        ContentValues contentValues=new ContentValues();
        contentValues.put(ContactContract.ContactEntry.NAME,name);
        contentValues.put(ContactContract.ContactEntry.EMAIL,email);

        String selection= ContactContract.ContactEntry.CONTACT_ID+ " = "+id;
        database.update(ContactContract.ContactEntry.TABLE_NAME,contentValues,selection,null);
    }

    // Delete Contact Details
    public void deleteContact(int id,SQLiteDatabase database){
        String selection= ContactContract.ContactEntry.CONTACT_ID+" = "+id;
        database.delete(ContactContract.ContactEntry.TABLE_NAME,selection,null);

    }
}
