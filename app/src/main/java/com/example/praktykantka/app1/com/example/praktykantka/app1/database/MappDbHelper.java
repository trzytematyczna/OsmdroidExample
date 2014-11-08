package com.example.praktykantka.app1.com.example.praktykantka.app1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Praktykantka on 2014-10-15.
 */
public class MappDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "MappDB.sqlite";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_DESCRIPTION_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TablesEntries.DescriptionEntry.TABLE_NAME + " (" +
                    TablesEntries.DescriptionEntry.COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    TablesEntries.DescriptionEntry.COLUMN_LANG + TEXT_TYPE + COMMA_SEP +
                    TablesEntries.DescriptionEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    TablesEntries.DescriptionEntry.COLUMN_DESC + TEXT_TYPE +
            " );";

    private static final String SQL_CREATE_ITEM_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TablesEntries.ItemEntry.TABLE_NAME + " (" +
                    TablesEntries.ItemEntry.COLUMN_ID +  " INTEGER PRIMARY KEY, "+
                    TablesEntries.ItemEntry.COLUMN_LAT+ TEXT_TYPE + COMMA_SEP +
                    TablesEntries.ItemEntry.COLUMN_LON + TEXT_TYPE +
            " );";

    private static final String SQL_DELETE_DESCRIPTION_TABLE =
            "DROP TABLE IF EXISTS " + TablesEntries.DescriptionEntry.TABLE_NAME +";";

    private static final String SQL_DELETE_ITEM_TABLE =
            "DROP TABLE IF EXISTS " + TablesEntries.ItemEntry.TABLE_NAME +";";

    public MappDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        Log.d("TableEntry", SQL_CREATE_DESCRIPTION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_DESCRIPTION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL(SQL_DELETE_DESCRIPTION_TABLE);
        sqLiteDatabase.execSQL(SQL_DELETE_ITEM_TABLE);
        onCreate(sqLiteDatabase);
    }
}
