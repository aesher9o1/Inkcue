package com.example.android.inkcue.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;


import com.example.android.inkcue.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by aesher on 8/8/2017.
 */





public class View_profile2 extends Fragment {

    @BindView(R.id.container)
    LinearLayout CONTAINER;


    @BindView(R.id.container2)
    LinearLayout CONTAINER2;

    @BindView(R.id.adding_View)
    ImageView addnow;

    @BindView(R.id.adding_View2)
    ImageView addnow2;

    @BindView(R.id.acheivement)
    EditText degrees;


    @BindView(R.id.clubs)
    EditText CLUB;

    @BindView(R.id.clubs2)
    EditText SOCIETY;

    @OnClick(R.id.next_fragment)
    public void GOTONEXT(){
        getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.addhere,new View_profile3()).commit();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_profile2,container,false);
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        ButterKnife.bind(this,v);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(getContext(),R.array.Degrees,android.R.layout.simple_spinner_item);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        addnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.add_degree,null);
                EditText degree2 = (EditText) addView.findViewById(R.id.acheivement2);
                degree2.setText(degrees.getText().toString());
                degrees.setText("");
                CONTAINER.addView(addView);
            }
        });


        addnow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.add_degree2,null);
                EditText degree2 = (EditText) addView.findViewById(R.id.clubs_secondary);
                EditText degree3 = (EditText) addView.findViewById(R.id.clubs_secondary2);
                degree3.setText(CLUB.getText().toString());
                degree2.setText(SOCIETY.getText().toString());
                CLUB.setText("");
                SOCIETY.setText("");
                CONTAINER2.addView(addView);
            }
        });



        ButterKnife.bind(this,v);
        return v;
    }
}
