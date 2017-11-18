package com.example.android.inkcue.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;



import com.example.android.inkcue.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by aesher on 7/10/2017.
 * The xml associated with the java file is view_profile this part is responsible for letting the users edit therir profile
 */

public class View_profile extends Fragment {



    /**
     *
     */

    @OnClick (R.id.next_fragment)
        public void GOTONEXT(){
        getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.addhere,new View_profile2()).commit();
    }




    @BindView(R.id.dateofbirth)
    TextView Date_of_birth;

    @BindView(R.id.human)
    ImageView HUMAN;



    @OnClick(R.id.dateofbirth)
         public void getting_the_date_now(){
                 final Calendar c = Calendar.getInstance();
                 DatePickerDialog.OnDateSetListener datePickerListner= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                 Date_of_birth.setText(""+year+"/"+month+"/"+dayOfMonth);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),datePickerListner, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.view_profile,container,false);
        ButterKnife.bind(this,v);

        return v;
    }



}
