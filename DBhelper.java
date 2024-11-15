package com.example.crudapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    // Constants for DB name and version
    private static final String DB_name = "courseDB";  // Database name
    private static final int DB_VERSION = 1;  // Database version

    public static final String TABLE_NAME = "users";  // Table name
    private static final String KEY_ID = "id";  // Column name for ID
    private static final String KEY_NAME = "name";  // Column name for Name

    // SQL query to create the table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT);";

    // Constructor
    public DBhelper(Context context, String input_dbName, Object o, int i) {
        super(context, DB_name, null, DB_VERSION);  // Passing constants to the superclass
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the query to create the table
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table and create a new one on version change
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
