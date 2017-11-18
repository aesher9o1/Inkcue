package com.example.android.inkcue.pref_and_requestes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.android.inkcue.activities.Login;
import com.example.android.inkcue.activities.MainActivity;
import com.example.android.inkcue.activities.fingureprint;

/**
 * Created by aesher on 8/12/2017.
 */

public class login_preference {


    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    final int PRIVATE_MODE = 0;
    Context _context;

    static final String Preference_Name ="Login_handler_preference";
    static final String IS_LOGIN = "IsLoggedIn";
    static final String IS_WANT_FINGUER = "Want_finguer";


    public login_preference(Context mContext){
        this.preferences = mContext.getSharedPreferences(Preference_Name,PRIVATE_MODE);
        this._context = mContext;
    }

    public  void CreateLoginSession(){
        if (this.editor == null) this.editor = this.preferences.edit();
        editor.putBoolean(IS_LOGIN, true);
        editor.clear();
        editor.apply();
    }


    public void SETFINGURE(boolean setting){
        if (this.editor == null)
            this.editor = this.preferences.edit();
        editor.putBoolean(IS_WANT_FINGUER, setting);
        editor.apply();
    }


    public boolean SEND_SCAN(){
        return preferences.getBoolean(IS_WANT_FINGUER,false);
    }

    public void REQUEST_SCAN(){
        if(SEND_SCAN()){
            Intent i = new Intent(_context,fingureprint.class);
            _context.startActivity(i);
        }
    }

    public void Logout_user(){
        if (this.editor == null)
            this.editor = this.preferences.edit();
        editor.putBoolean(IS_LOGIN, false);
        editor.apply();
    }

    private boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGIN, false);
    }

    public void Check_login(){
        if(!isLoggedIn()){
            Intent i = new Intent(_context, Login.class);
            _context.startActivity(i);
        }

        else{

            Intent i = new Intent(_context,  fingureprint.class);
            _context.startActivity(i);

        }
    }





}
