//package com.example.praktykantka.app1;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.support.v4.app.Fragment;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.app.ListFragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//
//import java.util.ArrayList;
//
//
//public class SimpleListActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener{
//
//    private Spinner spinner;
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        View view = inflater.inflate(R.layout.activity_list, container, false);
//        spinner = (Spinner)view.findViewById(R.id.spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//
//        return view;
//    }
//
//    public void onItemSelected(AdapterView<?> adapterView, View view,  int pos, long id) {
//
//    }
//
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
//
////        @Override
////        public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                Bundle savedInstanceState) {
////            View rootView = inflater.inflate(R.layout.fragment_list, container, false);
////            return rootView;
////        }
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.my, menu);
////        return true;
////    }
//
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        // Handle action bar item clicks here. The action bar will
////        // automatically handle clicks on the Home/Up button, so long
////        // as you specify a parent activity in AndroidManifest.xml.
////        int id = item.getItemId();
////        if (id == R.id.action_settings) {
////            return true;
////        }
////        return super.onOptionsItemSelected(item);
////    }
//
//
//}
