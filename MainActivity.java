package com.example.crudapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // EditTexts for user input
    EditText dbName, tableName, dbVersion, inputID, inputName, courseName;
    Button submit, read, update, delete, btn_tableCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initializing UI components
        submit = findViewById(R.id.btn_submit);
        read = findViewById(R.id.btn_read);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);
        btn_tableCreate = findViewById(R.id.btn_tableCreate);

        dbName = findViewById(R.id.et_dbName);
        tableName = findViewById(R.id.et_tableName);
        dbVersion = findViewById(R.id.et_dbVersion);
        inputID = findViewById(R.id.et_sid);
        inputName = findViewById(R.id.et_name);
        courseName = findViewById(R.id.et_course_name);

        // When the submit button is clicked
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditTexts
                String input_dbName = dbName.getText().toString();
                String input_tableName = tableName.getText().toString();
                String input_dbVersion = dbVersion.getText().toString();
                String input_ID = inputID.getText().toString();
                String input_name = inputName.getText().toString();
                String input_courseName = courseName.getText().toString();

                // Create an instance of DBhelper with the entered database name and version
                DBhelper dbHelper = new DBhelper(MainActivity.this, input_dbName, null, Integer.parseInt(input_dbVersion));

                // Open the database (either writable or readable)
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Check if the database is created successfully
                if (db != null) {
                    Toast.makeText(MainActivity.this, "Database Created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to Create Database", Toast.LENGTH_SHORT).show();
                }

                // Optional: Insert some initial data to verify the database is working
                // Insert into the "users" table
                db.execSQL("INSERT INTO users (name) VALUES ('" + input_name + "');");
            }
        });
    }
}
