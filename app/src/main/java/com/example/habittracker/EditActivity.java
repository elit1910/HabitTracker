package com.example.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.habittracker.data.HabitContract.HabitEntry;
import com.example.habittracker.data.HabitDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by estlander on 09/05/2017.
 */

public class EditActivity extends AppCompatActivity {

    private EditText mNameEditText;

    private EditText mDateEditText;

    private Spinner mPomoSpinner;

    private EditText mCommentsEditText;
    private int mPomo = 0;
    private int mMinutes = 0;


    private String mCurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);




    mNameEditText = (EditText) findViewById(R.id.edit_habit_name);
    mDateEditText = (EditText) findViewById(R.id.edit_start_date);
    mPomoSpinner = (Spinner) findViewById(R.id.spinner_Pomodoros);
    mCommentsEditText = (EditText) findViewById(R.id.comments);


        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mCurrentDate =  sdf.format(c.getTime());
        Log.d("Edit", mCurrentDate);
        mDateEditText.setText(mCurrentDate);

    setupSpinner();
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter pomoSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_amount_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        pomoSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mPomoSpinner.setAdapter(pomoSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mPomoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.a0))) {
                        mPomo = HabitEntry.NOLL;
                    }
                    else if (selection.equals(getString(R.string.a1))) {
                        mPomo = HabitEntry.ETT;
                    }
                    else if (selection.equals(getString(R.string.a2))) {
                        mPomo = HabitEntry.TVA;
                    }
                    else if (selection.equals(getString(R.string.a3))) {
                        mPomo = HabitEntry.TRE;
                    }
                    else if (selection.equals(getString(R.string.a4))) {
                        mPomo = HabitEntry.FYRA;
                    }
                    else if (selection.equals(getString(R.string.a5))) {
                        mPomo = HabitEntry.FEM;
                    }
                    else if (selection.equals(getString(R.string.a6))) {
                        mPomo = HabitEntry.SEX;
                    }
                    else if (selection.equals(getString(R.string.a7))) {
                        mPomo = HabitEntry.SJU;
                    }
                    else if (selection.equals(getString(R.string.a8))) {
                        mPomo = HabitEntry.ATTA;
                    }
                    else if (selection.equals(getString(R.string.a9))) {
                        mPomo = HabitEntry.NIO;
                    }
                    else if (selection.equals(getString(R.string.a10))) {
                        mPomo = HabitEntry.TIO;
                    }
                    else {
                        mPomo = HabitEntry.NOLL;
                    }
                }
                mMinutes = mPomo*25;
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPomo = 0; // Unknown
            }
        });
    }
    private void insertPet() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String dateString = mDateEditText.getText().toString();
        String commentstString = mCommentsEditText.getText().toString();
        String minutesString = String. valueOf(mMinutes) + " min";

        // Create database helper
       HabitDbHelper mDbHelper = new HabitDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_NAME, nameString);
        values.put(HabitEntry.COLUMN_START_DATE, dateString);
        values.put(HabitEntry.COLUMN_NUMBER_OF_POMODORO, mPomo);
        values.put(HabitEntry.COLUMN_COMMENTS, commentstString);
        values.put(HabitEntry.COLUMN_TOTAL_MINUTES, minutesString);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving Habit", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Habit saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Savee pet to data
                insertPet();
                //exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            //case R.id.action_delete:
                // Do nothing for now
                //return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
