//package com.example.praktykantka.app1;
//
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.app.ListFragment;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.os.Build;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//
///**
// * Shows List of added points after selecting Points from option menu
// */
//
//public class PointsListActivity extends FragmentActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list);
//        if (savedInstanceState == null) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            fragmentTransaction.add(R.id.container, fragment);
//            fragmentTransaction.commit();
//        }
//    }
//
//
//    public static class PlaceholderFragment extends ListFragment {
//        private MappDbHelper mappDbHelper;
//        ArrayAdapter<String> arrayAdapter;
//        Spinner spinner;
//        public PlaceholderFragment() {
//        }
//
//        public void onActivityCreated(Bundle savedInstanceState) {
//            super.onActivityCreated(savedInstanceState);
////            spinner = (Spinner) getView().findViewById(R.id.spinner);
////            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.languages, android.R.layout.simple_spinner_item);
////            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////            spinner.setAdapter(adapter);
//            arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, getAll());
//            setListAdapter(arrayAdapter);
//        }
//
//        public ArrayList<String> getAll() {
//            ArrayList<String> points = new ArrayList<String>();
//            String selectQuery = "SELECT  * FROM " +  TablesEntries.DescriptionEntry.TABLE_NAME;
//            mappDbHelper = new MappDbHelper(getActivity());
//
//            SQLiteDatabase db = mappDbHelper.getReadableDatabase();
//            Cursor cursor = db.rawQuery(selectQuery, null);
//
//            String  point;
//
//            try {
//                if (cursor.moveToFirst()) {
//                    do {
//                        point = (cursor.getString(2));
//                        points.add(point);
//                    } while (cursor.moveToNext());
//                }
//            }
//            finally{
//                cursor.close();
//                db.close();
//                mappDbHelper.close();
//            }
//            return points;
//        }
//
//    } //Placeholder
//}
