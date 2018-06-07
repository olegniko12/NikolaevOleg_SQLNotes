package olegnikolaev.mycontactapp;

import android.R.*;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    EditText uName;
    EditText uGrade;
    EditText uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        Log.d("MyContactApp", "MainActivity: instantiated DatabaseHelper");

        uName = findViewById(R.id.studentName);
        uGrade = findViewById(R.id.studentGrade);
        uID = findViewById(R.id.studentID);

        Log.d("MyContactApp", "MainActivity: instantiated user info");
    }

    public void ClearInputs(View view){
        if (uName.getText().toString().equals("")){
            Log.d("MyContactApp","MainActivity: " +uName.getText().toString() + " " + uGrade.getText().toString());
        }

        uName.setText("");
        uGrade.setText("");
        uID.setText("");

    }

    public void ClearDataBase(View v){
        dbHelper.ClearDatabase();
    }

    public void SaveInfo(View view){
        dbHelper.insertData(uName.getText().toString(), uGrade.getText().toString(), uID.getText().toString());
        //dbHelper.addData(uName.getText().toString(), uGrade.getText().toString(), uID.getText().toString());
        Log.d("MyContactApp", "MainActivity: inserted data from text input");
    }

    public void showMessage(String  title, String message){
        Log.d("MyContactApp", "MainActivity: showMessage: assembling AlertDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public static final String EXTRA_MESSAGE = "com.example.olegnikolaev.mycontactapp.MESSAGE";


    public void SearchRecord(View view){

        Cursor cursor = dbHelper.searchData(uName.getText().toString(), uGrade.getText().toString());
        Intent intent = new Intent(this, SearchActivity.class);
        StringBuffer buffer = new StringBuffer();
        int results = 0;
        while ((cursor.moveToNext())){
            if(cursor.getString(1).equals(uName.getText().toString())){
                Log.d("MyContactApp", "MainActivity: getting name");
                buffer.append("\nName: " + cursor.getString(1));
                Log.d("MyContactApp", "MainActivity: getting grade");
                buffer.append("\nGrade: " + cursor.getString(2));
                Log.d("MyContactApp", "MainActivity: getting SID");
                buffer.append("\nStudentID: " + cursor.getString(3));
                Log.d("MyContactApp", "MainActivity: full append: \n" + buffer.toString());
                results++;
            }

            Toast.makeText(this, "Full append:", Toast.LENGTH_SHORT);
        }
        showMessage("Contacts", buffer.toString());

        if(results == 0){
            Toast.makeText(this, "No results found", Toast.LENGTH_SHORT);
        }

        intent.putExtra(EXTRA_MESSAGE, buffer.toString());
        //startActivity(intent);

        /*
        Log.d("MyContactApp", "MainActivity: Launching SearchActivity");
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(EXTRA_MESSAGE, CreateFullInfo());
        Bundle infoBundle = new Bundle();
        infoBundle.putString("StudentName",uName.getText().toString());
        infoBundle.putString("StudentGrade",uGrade.getText().toString());
        infoBundle.putString("StudentID",uID.getText().toString());
        infoBundle.putStringArrayList("ReturnList", dbHelper.searchData(uName.getText().toString(), uGrade.getText().toString()));
        intent.putExtras(infoBundle);
        startActivity((intent));
        */
    }




}
//https://www.tutorialspoint.com/sqlite/sqlite_where_clause.htm