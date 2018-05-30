package olegnikolaev.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact2018.db";
    public static final String TABLE_NAME = "Contact2018_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String COLUMN_NAME_GRADE = "grade";
    public static final String COLUMN_NAME_STUDENTID = "sid";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID +  " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_CONTACT + " TEXT," +
                    COLUMN_NAME_GRADE + " TEXT," +
                    COLUMN_NAME_STUDENTID + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase(); //Delete later on
        Log.d("MyContactApp", "Databasehelper: constructed Databasehelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyContactApp", "Databasehelper: creating database");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyContactApp", "Databasehelper: upgrading database");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean insertData(String name, String grade, String sid){
        Log.d("MyContactApp", "Databasehelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues contentValues = new android.content.ContentValues();
        contentValues.put(COLUMN_NAME_CONTACT, name);
        contentValues.put(COLUMN_NAME_GRADE, grade);
        contentValues.put(COLUMN_NAME_STUDENTID, sid);

        long result = db.insert(TABLE_NAME, null , contentValues);
        if (result == -1){
            Log.d("MyContactApp", "Databasehelper: Contact insert failed");
            return false;
        } else {
            Log.d("MyContactApp", "Databasehelper: Contact insert passed");
            return true;
        }
    }

    Cursor cursor;

    cursor = db.raqQuery("SELECT * FROM " + )
    //https://stackoverflow.com/questions/29292569/android-how-can-i-search-data-in-sqlite-database-and-display-it-in-listview
}