package olegnikolaev.mycontactapp;

import android.R.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    EditText uName;
    EditText uGrade;
    EditText uID;

    EditText[] inputArray = new EditText[3];

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

        inputArray[0] = uName;
        inputArray[1] = uGrade;
        inputArray[2] = uID;
    }

    public void ClearInputs(View view){
        for (EditText eT: inputArray){
            eT.setText("");
        }
    }

    public void SaveInfo(View view){
        dbHelper.insertData(uName.toString(), uGrade.toString(), uID.toString());
        Log.d("MyContactApp", "MainActivity: inserted data from text input");
    }

    public void showMessage(String  title, String message){

    }

    public String CreateFullInfo(){

        String newString = "";
        for (EditText eT: inputArray){
            newString += eT.getText().toString() + " ";
        }
        return newString;
    }

    public static final String EXTRA_MESSAGE = "com.example.olegnikolaev.mycontactapp.MESSAGE";

    public void SearchRecord(View view){
        Log.d("MyContactApp", "MainActivity: Launching SearchActivity");
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(EXTRA_MESSAGE, CreateFullInfo());
        Bundle infoBundle = new Bundle();
        infoBundle.putString("StudentName",uName.getText().toString());
        infoBundle.putString("StudentGrade",uGrade.getText().toString());
        infoBundle.putString("StudentID",uID.getText().toString());
        intent.putExtras(infoBundle);
        startActivity((intent));
    }

}
//https://www.tutorialspoint.com/sqlite/sqlite_where_clause.htm