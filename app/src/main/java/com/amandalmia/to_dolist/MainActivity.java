package com.amandalmia.to_dolist;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //declare the variables
    EditText input;
    Button addNote;
    LinearLayout notes;
    SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //link the variables with their ids
        addNote = (Button) findViewById(R.id.add_note);
        input = (EditText) findViewById(R.id.input);
        notes = (LinearLayout) findViewById(R.id.notes);
        db = new SQLiteHandler(getApplicationContext());
        ArrayList<String> storedData = db.getData();
        if(storedData.size()>0){
            TextView newView = new TextView(this);
            for(int i = 0; i<storedData.size();i++){
                newView.setText(storedData.get(i));
                notes.addView(newView);
            }
        }
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This time we declare a TextView programmatically
                //Initialise an empty textView
                TextView tv = new TextView(MainActivity.this);
                //set the text as the user input
                tv.setText(input.getText().toString());
                //add the textView to the LinearLayout defined above
                notes.addView(tv);
                db.addData(input.getText().toString());
                //Remove whatever was written once add is pressed.
                input.setText("");
            }
        });
    }
}
