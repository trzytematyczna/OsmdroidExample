package com.example.praktykantka.app1;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IMapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

/**
 * Created by Praktykantka on 2014-10-15.
 */
public class MItemizedOver extends ItemizedOverlay<OverlayItem> {

    private ArrayList<OverlayItem> itemsList = new ArrayList<OverlayItem>();
    private Context context;

    public MItemizedOver(Drawable defaultMarker, Context context) {
        super(defaultMarker, new DefaultResourceProxyImpl(context));
//        super(boundCenterBottom(defaultMarker, OverlayItem.HotspotPlace.BOTTOM_CENTER));
//        this(defaultMarker);
        this.context = context;
    }

    @Override
    protected OverlayItem createItem(int i) {
        return itemsList.get(i);
    }

    @Override
    public int size() {
        return itemsList.size();
    }

    @Override
    protected boolean onTap(int index) {
        OverlayItem item = itemsList.get(index);
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(item.getTitle());
        dialog.setMessage(item.getSnippet());
        dialog.show();
        return true;
    }

    @Override
    public boolean onSnapToItem(int x, int y, Point snapPoint, IMapView mapView) {
        return false;
    }

//////
    public void addOverlay(OverlayItem overlay) {
        itemsList.add(overlay);
        this.populate();
    }


}
