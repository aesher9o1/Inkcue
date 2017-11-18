package com.example.android.inkcue.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.inkcue.R;
import com.example.android.inkcue.pref_and_requestes.pagerDashboard_detailAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aesher on 8/23/2017.
 */

public class Event_desc  extends AppCompatActivity{

    @BindView(R.id.tab)
    TabLayout top;

    @BindView(R.id.viewpager)
    ViewPager Pagerfragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);

        Pagerfragments.setOffscreenPageLimit(3);
        top.setupWithViewPager(Pagerfragments);

        pagerDashboard_detailAdapter adapter = new pagerDashboard_detailAdapter(getSupportFragmentManager());
        Pagerfragments.setAdapter(adapter);
    }


}
