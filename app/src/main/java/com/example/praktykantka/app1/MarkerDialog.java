package com.example.praktykantka.app1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Praktykantka on 2014-10-15.
 */
public class MarkerDialog  extends DialogFragment implements AdapterView.OnItemSelectedListener {

    private EditText mTitle;
    private EditText mDesc;
//    private Button save;
//    private Button cancel;
    private MapActivity mCallback;
    private Spinner spinner;
    public MarkerDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///TODO: set retained instance // ?
        if (savedInstanceState != null){
            mTitle.setText(savedInstanceState.getString("title"));
            mDesc.setText(savedInstanceState.getString("desc"));
        }

        setRetainInstance(true);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (MapActivity) getActivity();//getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Maker dialog, onCreate");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", mTitle.getText().toString());
        outState.putString("desc",mDesc.getText().toString());
        outState.putInt("spinner",spinner.getSelectedItemPosition());
    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//

//    }
public Dialog onCreateDialog(Bundle savedInstanceState) {

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    View view = getActivity().getLayoutInflater().inflate(R.layout.layout_marker_dialog, null);
    mTitle= (EditText) view.findViewById(R.id.marker_title);
    mDesc= (EditText) view.findViewById(R.id.marker_description);

    spinner = (Spinner) view.findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.languages, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(this);

    builder.setView(view);
    builder.setMessage("Save?")
            .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    mCallback.onSaveClick(mTitle.getText().toString(), mDesc.getText().toString(), spinner.getSelectedItem().toString());
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int id) {
            mCallback.onCancelClick();
        }
    });
    return builder.create();
}


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view,  int pos, long id) {
        switch (pos) {
            case 0:
//                    mDesc.setText("");
//                    mTitle.setText("");
                    break;
            case 1:
//                    mDesc.setText("");
//                    mTitle.setText("");
                    break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }
}


/* TODO: out ?
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_marker_dialog, container);
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        getDialog().setTitle("Save");
        save = (Button) view.findViewById(R.id.button_save);
        cancel= (Button) view.findViewById(R.id.button_cancel);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
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

    }*/