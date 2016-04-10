/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 */
package com.amandalmia.to_dolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version - Change the version if you make any change in the table structures
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Introduction";

    //Table Name
    private static final String TABLE_DATA = "data";

    //Column Name
    private static final String KEY_ID = "_id";
    private static final String KEY_VALUE = "value";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //SQL command to create our data table
        String CREATE_DATA_TABLE = "CREATE TABLE " + TABLE_DATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_VALUE + " TEXT"
                + ")";

        //executing the command
        db.execSQL(CREATE_DATA_TABLE);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        // Create tables again
        onCreate(db);
    }

    /**
     * Add data to our database
     *
     * @param value - value to be stored
     */
    public void addData(String value) {
        //get an instance of writable database
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues are passed to the query as parameters
        //to be entered in the database
        //They use the concept of Key/Value Pair
        ContentValues values = new ContentValues();
        values.put(KEY_VALUE, value);

        // Inserting Row
        long id = db.insert(TABLE_DATA, null, values);
        // Closing database connection
        db.close();
    }


    public ArrayList<String> getData() {
        //define empty ArrayList
        ArrayList<String> data = new ArrayList<>();
        //Query to get the data
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;
        int i = 0;
        // Since we read data, so we need a readable database
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor reads each row of the database
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Move to first row
        cursor.moveToFirst();
        //we run the loop till cursor has reached its end
        //Therefore cursor.ifAfterLast returns true in the next loop
        while (cursor.isAfterLast() == false) {
            //value is stored in the second column
            //cursor feeds value from that column to the ArrayList
            data.add(i, cursor.getString(1));
            //move the cursor to the next row
            cursor.moveToNext();
            i++;

        }
        //always close your cursors
        cursor.close();
        return data;
    }


    /**
     * Re crate database Delete all tables and create them again
     */
    public void deleteUsers() {
        //get an instance of the writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(db);
    }

}
