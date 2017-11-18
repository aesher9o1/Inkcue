package com.example.android.inkcue.activities.Event_desc_fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.inkcue.R;

/**
 * Created by aesher on 8/31/2017.
 */

public class Event_description extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_fragment_eventdesc,container,false);
        return v;
    }
}
