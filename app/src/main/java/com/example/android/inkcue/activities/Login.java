package com.example.android.inkcue.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.inkcue.R;
import com.example.android.inkcue.fragments.LogDetails;


import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {
    /**
     * @param  loginButton-> the loggin button of the facebook
     * @param  callbackManager -> The call back that the facebook thing receivers
     *
     */


    private int[] layouts;
    ViewPager viewPager;
    Button LOGIN,Register;
    Boolean Signin= false;


    @OnClick(R.id.register)
    public void REGISTER(View v){
        startActivity(new Intent(this,Register.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.ppager);
        LOGIN = (Button) findViewById(R.id.login);
        Register= (Button) findViewById(R.id.register);
        layouts = new int[]{R.layout.welcome_1,R.layout.welcome_1,R.layout.welcome_1 };




        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(!Signin){
                   getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.login_enter,android.R.anim.slide_out_right).replace(R.id.pager,new LogDetails()).commit();
                   viewPager.setVisibility(View.INVISIBLE);
                   LOGIN.setText("Troubles?");
                   LOGIN.setTextSize(17);
                   Register.setTextSize(18);
                   Signin= true;
               }

               else{
                   Toast.makeText(getApplicationContext(),"Now navigate to Problem Signin",Toast.LENGTH_SHORT).show();
                }



            }
        });

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter();

        viewPager.setAdapter(myViewPagerAdapter);





        //facebook's method





    }



    private class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }




}







