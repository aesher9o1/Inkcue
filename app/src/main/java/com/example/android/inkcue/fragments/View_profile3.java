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
import butterknife.OnClick;


/**
 * Created by aesher on 8/21/2017.
 */

public class View_profile3 extends Fragment {
    @Nullable

    @OnClick(R.id.next_fragment)
    public void GOTONEXT(){
        getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.addhere,new ViewProfile4()).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_profile3,container,false);
        ButterKnife.bind(this,v);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(getContext(),R.array.Degrees,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return v;
    }
}
