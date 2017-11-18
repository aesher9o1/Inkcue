package com.example.android.inkcue.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.android.inkcue.R;

import butterknife.ButterKnife;

/**
 * Created by aesher on 8/21/2017.
 */

public class ViewProfile4 extends Fragment {

    @Nullable


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_profile4,container,false);
        ButterKnife.bind(this,v);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        Spinner spin = (Spinner) v.findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(getContext(),R.array.Degrees,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spin.setAdapter(adapter);

        return v;
    }
}
