package com.example.android.inkcue.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.inkcue.R;
import javax.crypto.Cipher;

import com.example.android.inkcue.pref_and_requestes.FingerprintHelper;
import com.example.android.inkcue.pref_and_requestes.login_preference;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aesher on 8/11/2017.
 */




public class fingureprint extends AppCompatActivity implements FingerprintHelper.FingerprintHelperListener{
    @Nullable

    login_preference LoginWarder;
    Cipher cipher;
    FingerprintHelper fingerprintHelper;
    UserLoginTask mAuthTask;
    FingerprintManager fingerprintManager;
    FingerprintManager.CryptoObject cryptoObject;




    @OnClick(R.id.take_me_back)
    public void USER_Back(View v) {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }




    @BindView(R.id.errorPrinter)
    TextView ErrorPrinting;




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fingure);
        LoginWarder = new login_preference(this);
        ButterKnife.bind(this);

        if (LoginWarder.SEND_SCAN()) {
            attemptFingerprintLogin();

        } else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }



    private void attemptFingerprintLogin() {
        if (!testFingerPrintSettings())
            return;
        mAuthTask = new UserLoginTask();
        mAuthTask.execute((Void) null);
    }



    public void print(String text) {
        ErrorPrinting.setText(text);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean initCryptObject() {
        try {
            cryptoObject = new FingerprintManager.CryptoObject(cipher);
            return true;
        } catch (Exception ex) {
                Toast.makeText(this,"What A Terrible Failure(WTF)",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @SuppressLint("NewApi")
    private boolean testFingerPrintSettings() {
        print("Testing Fingerprint Settings");

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            print("This Android version does not support fingerprint authentication.");
            return false;
        }

        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if (!keyguardManager.isKeyguardSecure()) {
            print("User hasn't enabled Lock Screen");
            return false;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            print("User hasn't granted permission to use Fingerprint");
            return false;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            print("User hasn't registered any fingerprints");
            return false;
        }

        print("Fingerprint authentication is set.\n");

        return true;
    }



    @Override
    public void authenticationFailed(String error) {
        print("Some Error Occurred");
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void authenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        print("Authentication succeeded!");
        cipher = result.getCryptoObject().getCipher();
        startActivity(new Intent(this,MainActivity.class));
    }





    private class UserLoginTask extends AsyncTask<Void,Void,Boolean>{

        UserLoginTask() {
            fingerprintHelper = new FingerprintHelper(fingureprint.this);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected Boolean doInBackground(Void... params) {
            initCryptObject();
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            onCancelled();

            fingerprintHelper.startAuth(fingureprint.this.fingerprintManager, cryptoObject);

        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (fingerprintHelper != null)
            fingerprintHelper.cancel();
        if (mAuthTask != null)
            mAuthTask.cancel(true);
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (fingerprintHelper != null)
            fingerprintHelper.cancel();
        if (mAuthTask != null)
            mAuthTask.cancel(true);
    }




}