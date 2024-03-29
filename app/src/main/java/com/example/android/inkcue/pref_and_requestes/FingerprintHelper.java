package com.example.android.inkcue.pref_and_requestes;

import android.annotation.TargetApi;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;


@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHelper extends FingerprintManager.AuthenticationCallback {
    private FingerprintHelperListener listener;

    public FingerprintHelper(FingerprintHelperListener listener) {
        this.listener = listener;
    }

    private CancellationSignal cancellationSignal;

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();

        try {
            manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
        } catch (SecurityException ex) {
            listener.authenticationFailed("An error occurred:\n" + ex.getMessage());
        } catch (Exception ex) {
            listener.authenticationFailed("An error occurred\n" + ex.getMessage());
        }
    }

    public void cancel() {
        if (cancellationSignal != null)
            cancellationSignal.cancel();
    }

    public interface FingerprintHelperListener {
        void authenticationFailed(String error);
        void authenticationSucceeded(FingerprintManager.AuthenticationResult result);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        listener.authenticationFailed("Authentication error\n" + errString);
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        listener.authenticationFailed("Authentication help\n" + helpString);
    }

    @Override
    public void onAuthenticationFailed() {
        listener.authenticationFailed("Authentication failed.");
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        listener.authenticationSucceeded(result);
    }
}