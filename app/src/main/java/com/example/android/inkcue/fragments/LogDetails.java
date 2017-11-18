package com.example.android.inkcue.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.inkcue.R;
import com.example.android.inkcue.activities.MainActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.inkcue.pref_and_requestes.login_preference;


/**
 * Created by aesher on 7/10/2017.
 */

public class LogDetails extends Fragment implements GoogleApiClient.OnConnectionFailedListener{

    FloatingActionButton Google_but,Facebook_but;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    Button CONTINUE;
    login_preference Login_warder;


    @BindView(R.id.logname)
    EditText UserName;

    @BindView(R.id.logpassword)
    EditText UserPassword;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.logdetails,container,false);
        Google_but = (FloatingActionButton) v.findViewById(R.id.Google_button);
        Facebook_but = (FloatingActionButton) v.findViewById(R.id.Facebook_button);
        CONTINUE = (Button) v.findViewById(R.id.proceed);
        ButterKnife.bind(this,v);
        Login_warder = new login_preference(getContext());


        CONTINUE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserPassword.getText().toString().equals("admin")){
                    Intent i = new Intent(getContext(),MainActivity.class);
                    startActivity(i);
                    Login_warder.CreateLoginSession();
                    getActivity().finish();
                }
            }
        });






        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage((FragmentActivity) getContext(),  this )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        Google_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, 9001);
            }
        });


        Facebook_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions((Activity) getContext(), Arrays.asList("user_photos","email","user_birthday", "public_profile"));
            }
        });










         /*
        Callback manager ap even logger etc are for facebook
         */
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Login_warder.CreateLoginSession();
                Intent i = new Intent(getContext(),MainActivity.class);
                startActivity(i);
                getActivity().finish();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });







        return v;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode,resultCode,data);

        //below here all is google

        if (requestCode == 9001) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            Login_warder.CreateLoginSession();
            Intent i = new Intent(getContext(),MainActivity.class);
            startActivity(i);
            getActivity().finish();
        }
    }

    /**
     * This is responsible for facebook thing
     *
     */



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(getContext(),"Please check your internet connection",Toast.LENGTH_SHORT).show();


    }


}
