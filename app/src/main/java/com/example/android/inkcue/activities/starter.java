package com.example.android.inkcue.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.android.inkcue.pref_and_requestes.login_preference;
/**
 * Created by aesher on 8/12/2017.
 */

public class starter  extends Activity {

    login_preference StarterWarden;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        StarterWarden = new login_preference(this);
        StarterWarden.Check_login();
        finish();
    }
}
