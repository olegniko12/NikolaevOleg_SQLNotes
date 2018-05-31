package olegnikolaev.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SearchActivity extends AppCompatActivity {

    private String sName;
    private String sGrade;
    private String sID;

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

        Bundle b = getIntent().getExtras();

        sName = b.getString("StudentName");
        sGrade = b.getString("StudentGrade");
        sID = b.getString("StudentID");

    }

 //Should only display results, not actually search.

}
