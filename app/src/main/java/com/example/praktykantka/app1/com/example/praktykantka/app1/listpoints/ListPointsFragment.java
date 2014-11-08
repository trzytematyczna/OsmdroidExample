package com.example.praktykantka.app1.com.example.praktykantka.app1.listpoints;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.praktykantka.app1.com.example.praktykantka.app1.database.MappDbHelper;
import com.example.praktykantka.app1.com.example.praktykantka.app1.database.TablesEntries;

import java.util.ArrayList;

/**
 * Created by Praktykantka on 2014-10-22.
 */
public class ListPointsFragment extends ListFragment{

    private String TAG = "ListPointsFragment";
    private MappDbHelper mappDbHelper;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> data;
//    public ListPointsFragment() {
//    }



    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//            spinner = (Spinner) getView().findViewById(R.id.spinner);
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.languages, android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
        data = getAllLanguage("polski");
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, data);
        setListAdapter(arrayAdapter);
        ListView list = getListView();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                data.get(position);
                Log.d(TAG, "onItemClick" + position);
            }
        });
//        arrayAdapter.registerDataSetObserver();
//        arrayAdapter.setNotifyOnChange(true);

//        arrayAdapter.notifyDataSetChanged();

    }

    public ArrayList<String> getAllLanguage(String lang) {
        ArrayList<String> points = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " +  TablesEntries.DescriptionEntry.TABLE_NAME +" WHERE "+ TablesEntries.DescriptionEntry.COLUMN_LANG + "= \""+ lang +"\";";

        mappDbHelper = new MappDbHelper(getActivity());

        SQLiteDatabase db = mappDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String  point;

        try {
            if (cursor.moveToFirst()) {
                do {
                    point = (cursor.getString(2));
                    points.add(point);
                } while (cursor.moveToNext());
            }
        }
        finally{
            cursor.close();
            db.close();
            mappDbHelper.close();
        }
        return points;
    }

    public void changeData(String lang) {
        arrayAdapter.clear();
        ArrayList<String> points = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " +  TablesEntries.DescriptionEntry.TABLE_NAME +" WHERE "+ TablesEntries.DescriptionEntry.COLUMN_LANG + "= \""+ lang +"\";";

        mappDbHelper = new MappDbHelper(getActivity());

        SQLiteDatabase db = mappDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String  point;

        try {
            if (cursor.moveToFirst()) {
                do {
                    point = (cursor.getString(2));
//                    points.add(point);
                    arrayAdapter.add(point);
                } while (cursor.moveToNext());
            }
        }
        finally{
            cursor.close();
            db.close();
            mappDbHelper.close();
        }
    }


    public void updateView(String lang) {
        changeData(lang);
//        arrayAdapter.clear();
//        data = getAllLanguage(lang);
//        arrayAdapter.notifyDataSetChanged();
//        ArrayList<String> asd = getAllLanguage(lang);
//        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
//        setListAdapter(arrayAdapter);
        Log.d(TAG, "updateView");
    }


    public ArrayList<String> getAll() {
        ArrayList<String> points = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " +  TablesEntries.DescriptionEntry.TABLE_NAME;
        mappDbHelper = new MappDbHelper(getActivity());

        SQLiteDatabase db = mappDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String  point;

        try {
            if (cursor.moveToFirst()) {
                do {
                    point = (cursor.getString(2));
                    points.add(point);
                } while (cursor.moveToNext());
            }
        }
        finally{
            cursor.close();
            db.close();
            mappDbHelper.close();
        }
        return points;
    }

    public void getPointInfo(int id) {
        String selectQuery = "SELECT  * FROM " +  TablesEntries.ItemEntry.TABLE_NAME +" WHERE "+ TablesEntries.ItemEntry.COLUMN_ID+ "= \""+ id +"\";";

        mappDbHelper = new MappDbHelper(getActivity());

        SQLiteDatabase db = mappDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String  lon;
        String lat;

        try {
            if (cursor.moveToFirst()) {
                do {
                    lon = (cursor.getString(0));
                    lon = (cursor.getString(1));
                } while (cursor.moveToNext());
            }
        }
        finally{
            cursor.close();
            db.close();
            mappDbHelper.close();
        }
    }
}
