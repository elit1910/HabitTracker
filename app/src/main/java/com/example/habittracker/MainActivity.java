package com.example.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.habittracker.data.HabitContract.HabitEntry;
import com.example.habittracker.data.HabitDbHelper;

import static com.example.habittracker.data.HabitContract.HabitEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new HabitDbHelper(this);

    }
    @Override
    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_NAME,
                HabitEntry.COLUMN_START_DATE,
                HabitEntry.COLUMN_NUMBER_OF_POMODORO,
                HabitEntry.COLUMN_COMMENTS,
                HabitEntry.COLUMN_TOTAL_MINUTES
        };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        try {
            // Satt mera stuff
            displayView.setText("The habits table contains " + cursor.getCount() + " habits. \n\n");
            displayView.append("ID - Activity name - Date - Amount Pomodoros - Comments \n\n");


            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME);
            int startTimeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_START_DATE);
            int pomodoroColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NUMBER_OF_POMODORO);
            int commentsColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_COMMENTS);
            int totalMinutesColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_TOTAL_MINUTES);

            while(cursor.moveToNext()){
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentDate = cursor.getString(startTimeColumnIndex);
                int currentNumberOfPomo = cursor.getInt(pomodoroColumnIndex);
                String currentTextComments =cursor.getString(commentsColumnIndex);
                int currentMinutes = cursor.getInt(totalMinutesColumnIndex);


                //av nagon anledning en extra parentes i exempelkoden
                displayView.append("\n" + currentID + " - " +
                        currentName + " - " +
                        currentDate + " - " +
                        currentNumberOfPomo + " - " +
                        currentTextComments +" - " +
                        currentMinutes);
            };
    }finally {
        cursor.close();

        }
        }

    //private void deleteAllHabits() {
        //int rowsDeleted = getContentResolver().delete(HabitEntry.CONTENT_URI, null, null);
        //Log.v("MainActivity", rowsDeleted + " rows deleted from Habit database");
    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return true;
    }

    private void insert() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        values1.put(HabitEntry.COLUMN_NAME, "Drink water");
        values1.put(HabitEntry.COLUMN_NUMBER_OF_POMODORO, 2);
        values2.put(HabitEntry.COLUMN_NAME, "Walk my dog");
        values2.put(HabitEntry.COLUMN_NUMBER_OF_POMODORO, 1);
        db.insert(TABLE_NAME, null, values1);
        db.insert(TABLE_NAME, null, values2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insert();
                displayDatabaseInfo();
                return true;
            //case R.id.action_delete_all_entries:

                //deleteAllHabits();
                //return true;

        }
        return super.onOptionsItemSelected(item);
    }
}

