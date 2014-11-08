package com.example.praktykantka.app1.com.example.praktykantka.app1.listpoints;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.praktykantka.app1.R;

/**
 * Created by Praktykantka on 2014-10-22.
 */

public class PointsListFragmentActivity extends FragmentActivity implements UpPointsFragment.UpPointsFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_points_list);
    }

    @Override
    public void onLanguageChanged(String lang) {

        ListPointsFragment listPointsFragment=(ListPointsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_list_points);
        listPointsFragment.updateView(lang);
    }
}
