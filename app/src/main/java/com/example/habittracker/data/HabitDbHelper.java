package com.example.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.habittracker.data.HabitContract.HabitEntry;

import static com.example.habittracker.data.HabitContract.HabitEntry.TABLE_NAME;

/**
 * Created by estlander on 10/05/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper{

    public static final String LOG_TAG = HabitDbHelper.class.getName();

    private static final String DATABASE_NAME = "habittracker.db";

    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_START_DATE + " DATETIME NOT NULL DEFAULT CURRENT_DATE, "
                + HabitEntry.COLUMN_NUMBER_OF_POMODORO + " INTEGER NOT NULL DEFAULT 0, "
                + HabitEntry.COLUMN_COMMENTS + " TEXT, "
                + HabitEntry.COLUMN_TOTAL_MINUTES + " TEXT)";

        db.execSQL(SQL_CREATE_HABIT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
