package com.example.android.inkcue.pref_and_requestes;

/**
 * Created by aesher on 8/10/2017.
 */

public class notification_provider {
    private int myname;
    private int myIMAGE;

    public  notification_provider(int name , int IMAGE){
        this.myname=name;
        this.myIMAGE= IMAGE;
    }

   int getMyname(){return myname;}
    int getMyIMAGE() {return myIMAGE;}
}
