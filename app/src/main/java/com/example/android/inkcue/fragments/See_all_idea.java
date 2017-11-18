package com.example.android.inkcue.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import com.example.android.inkcue.R;


/**
 * Created by aesher on 8/31/2017.
 */

public class See_all_idea extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.all_idea,container,false);

        return v;
    }
}
