package com.example.android.inkcue.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.inkcue.R;
import com.example.android.inkcue.activities.Create_Idea;
import com.example.android.inkcue.activities.Event_desc;
import com.example.android.inkcue.pref_and_requestes.pagerDashboardADAPTER;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by aesher on 7/6/2017.
 * the xml file it is associated to is dashboard.xml this will load as soon as the activity is started
 */




public class Dashboard extends Fragment {

    /**
     * @param PROFILE -> the top user profile image on the project
     * @param Uname -> users full name
     * @param Uprofession -> Uer's profession
     * @param VProfile -> Button to view profile of the user
     * @param Cwritting -> Conitnue writting
     * @param LEFT-> LEft arrow of the pager view
     * @param RIGHT -> RIGHT arrow of the pager view
     * @param  Iname-> The name of the idea
     * @param Abstract-> The text written on the abstract Iname and Abstract would be taken into the next activity
     * @param IdeaScroll ->The view pager of the dashboad that scrolls through the idea or whatever currently not sure
     */




public
    ImageView PROFILE;
    TextView Uname,Uprofession;
    Button VProfile,Cwritting;
    ViewPager IdeaScroll;
    ImageView LEFT, RIGHT;

    int pagercount;
    private static final int Time = 1000;
    private long mBackPressed;



    pagerDashboardADAPTER padapter;



    @OnClick(R.id.fab)
    public void FLOATING_CREATE_IDEA(View v){
        Intent i = new Intent(getContext(),Create_Idea.class);
        startActivity(i);
    }

    @OnClick(R.id.see_submitted)
    public void GOTO_SEEALLIDEA(){
        getFragmentManager().beginTransaction().replace(R.id.addhere,new See_all_idea()).commit();
    }


    @BindView(R.id.fourth)
      CardView cardView;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.dashboard,container,false);

        /*
                  INITIALIZING THE VIEWS OF THE DASHBOARD :)
          */



        PROFILE= (ImageView) v.findViewById(R.id.profile);
        Uname = (TextView) v.findViewById(R.id.user_name);
        Uprofession = (TextView) v.findViewById(R.id.user_profession);
        VProfile = (Button) v.findViewById(R.id.viewpro);
        Cwritting = (Button) v.findViewById(R.id.continuewriting);
        IdeaScroll = (ViewPager) v.findViewById(R.id.pager);
        LEFT= (ImageView) v.findViewById(R.id.imageView3);
        RIGHT = (ImageView) v.findViewById(R.id.imageView2);


        padapter= new pagerDashboardADAPTER(getFragmentManager(),v);
        IdeaScroll.setAdapter(padapter);



        ButterKnife.bind(this,v);
        pagercount= padapter.getCount();





        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });




        LEFT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = IdeaScroll.getCurrentItem();            //this willl get the current position of the page scroller
                IdeaScroll.setCurrentItem(current-1);



            }
        });


        RIGHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int current = IdeaScroll.getCurrentItem();            //this willl get the current position of the page scroller
                IdeaScroll.setCurrentItem(current+1);


            }
        });


        return v;
    }


    /*
    The below function is responsible for setting the visibility and invisibility of the arrows
    */



}
