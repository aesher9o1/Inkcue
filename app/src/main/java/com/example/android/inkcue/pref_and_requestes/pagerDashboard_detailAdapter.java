package com.example.android.inkcue.pref_and_requestes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.inkcue.activities.Event_desc_fragments.Event_description;

/**
 * Created by aesher on 8/31/2017.
 */

public class pagerDashboard_detailAdapter extends FragmentStatePagerAdapter {

    private String [] title = {"Event Description","Details","Prizes","Specification"};

    public pagerDashboard_detailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 1: return new Event_description();
           default: return new Event_description();
       }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public int getItemPosition(Object object){
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
