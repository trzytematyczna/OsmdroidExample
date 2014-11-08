package com.example.praktykantka.app1.com.example.praktykantka.app1.listpoints;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.praktykantka.app1.R;
import com.example.praktykantka.app1.com.example.praktykantka.app1.database.MappDbHelper;

/**
 * Created by Praktykantka on 2014-10-22.
 */
public class UpPointsFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private String TAG = "UpPointsFragment ";
    private MappDbHelper mappDbHelper;
    ArrayAdapter<String> arrayAdapter;
    Spinner spinner;

    UpPointsFragmentListener activityCallback;
    private int languages;

    public interface UpPointsFragmentListener{
        public void onLanguageChanged(String selLang);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCallback = (UpPointsFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement UpPointsFragmentListener");
        }
    }
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_up_points,container, false);
            spinner = (Spinner) view.findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.languages, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        switch (pos) {
            case 0:
                Log.d(TAG, "1");
                activityCallback.onLanguageChanged("polski");
                break;
            case 1:
                activityCallback.onLanguageChanged("english");
                Log.d(TAG, "2");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
