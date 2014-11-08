package com.example.praktykantka.app1.com.example.praktykantka.app1.database;

import android.provider.BaseColumns;

/**
 * Created by Praktykantka on 2014-10-15.
 */
public final class TablesEntries {
    public TablesEntries(){
    }

    public static abstract class DescriptionEntry implements BaseColumns {
        public static final String TABLE_NAME = "DescriptionTable";
        public static final String COLUMN_ID= "itemId";
        public static final String COLUMN_LANG= "lang";
        public static final String COLUMN_TITLE= "title";
        public static final String COLUMN_DESC= "description";
    }
    public static abstract class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "ItemTable";
        public static final String COLUMN_ID= "id";
        public static final String COLUMN_LAT= "lat";
        public static final String COLUMN_LON= "lon";
    }
}