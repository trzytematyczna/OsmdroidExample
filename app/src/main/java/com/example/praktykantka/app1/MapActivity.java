package com.example.praktykantka.app1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.praktykantka.app1.com.example.praktykantka.app1.database.MappDbHelper;
import com.example.praktykantka.app1.com.example.praktykantka.app1.database.TablesEntries;
import com.example.praktykantka.app1.com.example.praktykantka.app1.listpoints.PointsListFragmentActivity;

import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;


public class MapActivity extends ActionBarActivity implements MapEventsReceiver, ClickListener
{

    private String TAG = "MapActivity";

    ArrayList<OverlayItem> items;
    private MapView mapView;
    MappDbHelper mappDbHelper;
    private ArrayList mSelectedItems;
    private GeoPoint current_geopoint;
//    protected String dialog_title;
//    String dialog_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);

        mapView = (MapView) findViewById(R.id.mapview);
        items = new ArrayList<OverlayItem>();

        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(15);

        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
        MItemizedOver mitamized = new MItemizedOver(drawable, this);
        mitamized.setEnabled(true);

        GeoPoint amistad = new GeoPoint(50.056900, 19.932866);

        String title = "Amistad";
        String desc = "Je Je Je Twórca najlepszych aplikacji mobilnych";

        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
        mapView.getOverlays().add(0, mapEventsOverlay);

        OverlayItem amistadItem = new OverlayItem(title, desc, amistad);
        items.add(amistadItem);

        mitamized.addOverlay(amistadItem);
        mapView.getOverlays().add(mitamized);
        mapView.getController().setCenter(amistad);


        current_geopoint=new GeoPoint(0,0);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            current_geopoint.setLatitudeE6(savedInstanceState.getInt("point_lat"));
            current_geopoint.setLongitudeE6(savedInstanceState.getInt("point_long"));
        }
//        saveToDB(title,desc);
//        mappDbHelper = new MappDbHelper(this.getApplicationContext());
//        SQLiteDatabase db = mappDbHelper.getWritableDatabase();
//
//        ContentValues val = new ContentValues();
//        val.put(TablesEntries.DescriptionEntry.COLUMN_LANG, "xxx");
//        val.put(TablesEntries.DescriptionEntry.COLUMN_TITLE, title);
//        val.put(TablesEntries.DescriptionEntry.COLUMN_DESC, desc);
//
//        long id = db.insert(TablesEntries.DescriptionEntry.TABLE_NAME, null,val);
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint geoPoint) {
        Toast.makeText(this, "Tapped", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override public boolean longPressHelper(GeoPoint geoPoint) {
        FragmentManager fm = getSupportFragmentManager();
        DialogFragment dialog = new MarkerDialog();
        dialog.show(fm,TAG);
//        dialog.setTargetFragment(this, 0);
//        FragmentManager fm = getSupportFragmentManager();
//        ClickListFragment clicklist = new ClickListFragment();
//        clicklist.showDialog(fm);
//        MarkerDialog2 editNameDialog = new MarkerDialog2();
//        editNameDialog.show(fm, "fragment");

//        DialogFragment dialog = new MarkerDialog();
//        dialog.setTargetFragment(dialog,0);
//        dialog.show(fm,TAG);

        Toast.makeText(this, "Long "+ geoPoint.getLatitude()+" "+geoPoint.getLongitude(), Toast.LENGTH_LONG).show();
        current_geopoint = geoPoint;
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
//            Intent intent = new Intent(getApplicationContext(),SimpleListActivity.class);
//            Intent intent = new Intent(getApplicationContext(),PointsListActivity.class);
            Intent intent = new Intent(getApplicationContext(),PointsListFragmentActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSaveClick(String mTitle, String mDesc, String lang) {
        saveToDB(mTitle,mDesc, lang);
        paintDrawable(mTitle, mDesc);
        Log.d(TAG, "onSaveClick");
    }

    public void onCancelClick() {
        Log.d(TAG, "onCancelClick");
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("point_long", current_geopoint.getLongitudeE6());
        savedInstanceState.putInt("point_lat", current_geopoint.getLatitudeE6());
        super.onSaveInstanceState(savedInstanceState);
    }

    public void saveToDB(String tit, String desc, String lang){
        // db saving
        mappDbHelper = new MappDbHelper(this.getApplicationContext());
        SQLiteDatabase db = mappDbHelper.getWritableDatabase();

        ContentValues descTableVal = new ContentValues();
        descTableVal.put(TablesEntries.DescriptionEntry.COLUMN_LANG, lang);
        descTableVal.put(TablesEntries.DescriptionEntry.COLUMN_TITLE, tit);
        descTableVal.put(TablesEntries.DescriptionEntry.COLUMN_DESC, desc);

        long idDesc = db.insert(TablesEntries.DescriptionEntry.TABLE_NAME, null,descTableVal);

        ContentValues itemTableVal = new ContentValues();

        itemTableVal.put(TablesEntries.ItemEntry.COLUMN_ID, idDesc);
        itemTableVal.put(TablesEntries.ItemEntry.COLUMN_LAT, current_geopoint.getLatitudeE6());
        itemTableVal.put(TablesEntries.ItemEntry.COLUMN_LON, current_geopoint.getLongitudeE6());

        long idItem = db.insert(TablesEntries.ItemEntry.TABLE_NAME, null,itemTableVal);

        db.close();
        mappDbHelper.close();
        Toast.makeText(this, "DB save", Toast.LENGTH_LONG).show();
    }

    public void paintDrawable(String tit, String desc){
        //drawable
        Toast.makeText(this, "DB" +tit + desc, Toast.LENGTH_SHORT).show();
        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
        MItemizedOver mitamized = new MItemizedOver(drawable, this);
        mitamized.setEnabled(true);

        OverlayItem item= new OverlayItem(tit, desc, current_geopoint);
        items.add(item);

        mitamized.addOverlay(item);
        mapView.getOverlays().add(mitamized);

        mapView.invalidate();

    }
/*
    public class  MarkerDialog2  extends DialogFragment {

        private EditText mTitle;
        private EditText mDesc;
        private Button save;
        private Button cancel;

        public MarkerDialog2() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.layout_marker_dialog, container);
            mTitle = (EditText) view.findViewById(R.id.marker_title);
            mDesc = (EditText) view.findViewById(R.id.marker_description);
            getDialog().setTitle("Save");
//            save = (Button) view.findViewById(R.id.button_save);
//            cancel= (Button) view.findViewById(R.id.button_cancel);

            save.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Save", Toast.LENGTH_SHORT).show();
                    Toast.makeText(view.getContext(), "save: "+mTitle.getText().toString()+ mDesc.getText().toString(), Toast.LENGTH_SHORT).show();
                    onSave(mTitle.getText().toString(), mDesc.getText().toString());
                    getDialog().dismiss();
                }
            });


            cancel.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                }
            });
            return view;

        }

    }
    public void onSave(String tit, String desc){
        // db saving
        mappDbHelper = new MappDbHelper(this.getApplicationContext());
        SQLiteDatabase db = mappDbHelper.getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put(TablesEntries.DescriptionEntry.COLUMN_LANG, "xxx");
        val.put(TablesEntries.DescriptionEntry.COLUMN_TITLE, tit);
        val.put(TablesEntries.DescriptionEntry.COLUMN_DESC, desc);

        long id = db.insert(TablesEntries.DescriptionEntry.TABLE_NAME, null,val);
        db.close();
        Toast.makeText(this, "DB save", Toast.LENGTH_LONG).show();

        //drawable
        Toast.makeText(this, "DB" +tit + desc, Toast.LENGTH_SHORT).show();
        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
        MItemizedOver mitamized = new MItemizedOver(drawable, this);
        mitamized.setEnabled(true);

        OverlayItem item= new OverlayItem(tit, desc, current_geopoint);
        items.add(item);

        mitamized.addOverlay(item);
        mapView.getOverlays().add(mitamized);

        mapView.invalidate();
    }
*/


}


