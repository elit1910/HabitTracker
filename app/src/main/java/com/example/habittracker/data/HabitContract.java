package com.example.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by estlander on 10/05/2017.
 */

public class HabitContract {

    private HabitContract(){

    }

    //public static final String CONTENT_AUTHORITY = "com.example.android.habittracker";
    //public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //public static final String PATH_HABITS = "data";


    public static final class HabitEntry implements BaseColumns{
        public final static String TABLE_NAME = "habits";

        //public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_HABITS);

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_START_DATE = "start_date";
        public final static String COLUMN_NUMBER_OF_POMODORO = "number_of_pomodoro";
        public final static String COLUMN_TOTAL_MINUTES = "total_minutes";
        public final static String COLUMN_COMMENTS = "comments";

        public static final int NOLL = 0;
        public static final int ETT = 1;
        public static final int TVA = 2;
        public static final int TRE = 3;
        public static final int FYRA = 4;
        public static final int FEM = 5;
        public static final int SEX = 6;
        public static final int SJU = 7;
        public static final int ATTA = 8;
        public static final int NIO = 9;
        public static final int TIO = 10;

    }

}
