package olegnikolaev.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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
                    COLUMN_NAME_STUDENTID + " TEXT);";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase(); //Delete later on
        Log.d("MyContactApp", "Databasehelper: constructed Databasehelper");
    }

    public void ClearDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
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

    public void addData(String name, String grade, String sid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('" + name + "', '" + grade + "', '" + sid + "')");
        Log.d("MyContactApp", "Databasehelper: adding data");
    }

    public Cursor searchData(String name, String grade){

        SQLiteDatabase db = this.getWritableDatabase();

        String query;

        Log.d("MyContactApp", "Retrieving data");

        if (name.length() > 0){
            if (grade.length() > 0){
                query = ("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_CONTACT + " = '" + name + "' AND " + COLUMN_NAME_GRADE + " = '" + grade + "'");
                Log.d("MyContactApp", "DatabaseHelper: Searching Data with the name and grade");
            } else {
                query = ("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_CONTACT + " = '" + name+ "'");
            }
        } else {
            if (grade.length() > 0){
                query = ("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_GRADE + " = '" + grade + "'");
            } else {
                query = ("SELECT * FROM " + TABLE_NAME);
            }
        }
        Log.d("DatabaseHelper", "searchData: QUERY: " + query);
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

}