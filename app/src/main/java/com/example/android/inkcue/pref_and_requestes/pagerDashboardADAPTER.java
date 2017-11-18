package com.example.android.inkcue.pref_and_requestes;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.inkcue.R;
import com.example.android.inkcue.activities.Event_desc;
import com.example.android.inkcue.fragments.Dashboard;

/**
 * Created by aesher on 7/7/2017.
 */

public class pagerDashboardADAPTER extends FragmentStatePagerAdapter {

    private static View AllView;

    public pagerDashboardADAPTER(FragmentManager fm , View view) {
        super(fm);
        AllView = view;
    }


    @Override
    public Fragment getItem(int position) {

        Fragment frag = new pagerFragment();
        Bundle arg = new Bundle();
        arg.putInt(pagerFragment.ARG_OBJECT,position + 1);              //sending argument sto the pager adapter
        frag.setArguments(arg);
        return frag;

    }


    @Override
    public int getCount() {
        return 5;
    }



    // The fragment class is here for the pagerdashboard




    public static class  pagerFragment extends Fragment{

        public static final String ARG_OBJECT = "object";

        RelativeLayout Rparent;
        TextView ViewTitle;



        @Override
        public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.pagerdashboard,container,false);

            /*

            Initialising viwes of the fragment pagerdashboard

            */

            Rparent = (RelativeLayout) v.findViewById(R.id.setback);
            ViewTitle = (TextView) v.findViewById(R.id.event);


            //getting the arguments from the adapter

            Bundle args = getArguments();
            String a=Integer.toString(args.getInt(ARG_OBJECT));
            ViewTitle.setText("The instance now is " + a);


            ViewTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(),Event_desc.class);
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity(),AllView.findViewById(R.id.pager),getString(R.string.event_desc)).toBundle();
                    startActivity(i,bundle);
                }
            });

            return v;
        }
    }
}













