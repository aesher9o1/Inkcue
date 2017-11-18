package com.example.android.inkcue.activities;


import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;
import com.example.android.inkcue.fragments.Dashboard;
import com.example.android.inkcue.R;
import com.example.android.inkcue.fragments.View_profile;
import com.example.android.inkcue.pref_and_requestes.notification_adapter;
import com.example.android.inkcue.pref_and_requestes.notification_provider;
import com.example.android.inkcue.pref_and_requestes.login_preference;
import com.facebook.CallbackManager;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView Dtoggle;
    Boolean isNotiOpen=false;
    private static final int Time = 2000;
    private long mBackPressed;

    notification_adapter Adapter;
    List<notification_provider> Provider;
    login_preference Pref_warden;
    CallbackManager callbackManager;
    /**
     * Binding all the views here from butterknife
      */

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.recycler_view1)
    RecyclerView Recycle;               //the notification recycler view

    @BindView(R.id.bell)
    ImageView Notification;

    @BindView(R.id.notification)
    ScrollView SHOW_NOTIFICATION;



    /**
     * Onclicks of items
     */


    @OnClick(R.id.barToggle)
        public void OPEN_DRAWER(View v){
            drawer.openDrawer(GravityCompat.START);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dtoggle= (ImageView) findViewById(R.id.barToggle);

        ButterKnife.bind(this);
        callbackManager = CallbackManager.Factory.create();
        Pref_warden = new login_preference(this);


        getSupportFragmentManager().beginTransaction().replace(R.id.addhere,new Dashboard()).commit();


        Provider = new ArrayList<>();
        //initialize the adapter
        Adapter = new notification_adapter(getApplicationContext(),Provider);

        //preperaing recycker view1
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        Recycle.setLayoutManager(layoutManager);
        Recycle.setHasFixedSize(true);
        Recycle.setAdapter(Adapter);
        Recycle.setNestedScrollingEnabled(false);




        demoVALUES();




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isNotiOpen){
                    int cx = SHOW_NOTIFICATION.getWidth();
                    int cy = SHOW_NOTIFICATION.getHeight();
                    float finalRadius = (float) Math.hypot(cx, cy)+100;
                    int CX = cx-80;
                    Animator anim = ViewAnimationUtils.createCircularReveal(SHOW_NOTIFICATION,CX,0,0,finalRadius);
                    SHOW_NOTIFICATION.setVisibility(View.VISIBLE);
                    anim.start();
                    isNotiOpen= true;
                }

                else  {
                    SHOW_NOTIFICATION.setVisibility(View.INVISIBLE);
                    isNotiOpen = false;
                }

            }
        });






    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(mBackPressed + Time> System.currentTimeMillis()){
                super.onBackPressed();
            }
            else {
                Toast.makeText(this,"Tap back button again in order to exit",Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.addhere,new Dashboard()).commit();
                mBackPressed=System.currentTimeMillis();
            }
        }
    }




    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        else {
            Toast.makeText(this,"This will be replaced",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            getSupportFragmentManager().beginTransaction().replace(R.id.addhere,new Dashboard()).commit();
        } else if (id == R.id.nav_gallery) {
            getSupportFragmentManager().beginTransaction().replace(R.id.addhere,new View_profile()).commit();

        } else if (id == R.id.nav_share) {

        }
        else if (id ==R.id.fingure_print){

            if(Build.VERSION.SDK_INT<23){Toast.makeText(this,"Sorry this feature is under development in your android version",Toast.LENGTH_SHORT).show();
            }
            else {

                if(Pref_warden.SEND_SCAN()){
                    Pref_warden.SETFINGURE(false);
                }
                else {
                    Pref_warden.SETFINGURE(true);
                }

            }


        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void demoVALUES() {
        notification_provider p1 = new notification_provider(R.string.Test, R.mipmap.ic_launcher);
        Provider.add(p1);

        p1 = new notification_provider(R.string.Test, R.mipmap.ic_launcher);
        Provider.add(p1);

        p1 = new notification_provider(R.string.Test, R.mipmap.ic_launcher);
        Provider.add(p1);

        p1 = new notification_provider(R.string.Test, R.mipmap.ic_launcher);
        Provider.add(p1);

        p1 = new notification_provider(R.string.Test, R.mipmap.ic_launcher);
        Provider.add(p1);

        p1 = new notification_provider(R.string.Test, R.mipmap.ic_launcher);
        Provider.add(p1);
        p1 = new notification_provider(R.string.Test, R.mipmap.ic_launcher);
        Provider.add(p1);

        p1 = new notification_provider(R.string.Test, R.mipmap.ic_launcher);
        Provider.add(p1);



    }

}
