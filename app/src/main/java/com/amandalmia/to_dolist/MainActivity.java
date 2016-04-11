package com.amandalmia.to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declare the variables
    EditText input;
    Button addNote;
    LinearLayout notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //link the variables with their ids
        addNote = (Button) findViewById(R.id.add_note);
        input = (EditText) findViewById(R.id.input);
        notes = (LinearLayout) findViewById(R.id.notes);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Storing and showing the user data
                Toast.makeText(MainActivity.this, input.getText().toString(), Toast.LENGTH_SHORT).show();
                //Remove whatever was written once add is pressed.
                input.setText("");
            }
        });
    }
}
