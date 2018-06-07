package olegnikolaev.mycontactapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private String sName;
    private String sGrade;
    private String sID;
    private ArrayList<String> infoList;

    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Get intent that initialized activity
        android.content.Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Capture the Layout's textView and set the string as the text
        android.widget.TextView textView = findViewById(R.id.textViewInfo);
        textView.setText(message);

        mainActivity = new MainActivity();

        showMessage("Contacts", intent.getStringExtra("Results"));

    }

    public void showMessage(String  title, String message){
        Log.d("MyContactApp", "SearchActivity: showMessage: assembling AlertDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


 //Should only display results, not actually search.

}
