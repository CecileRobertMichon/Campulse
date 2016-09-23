package com.campulse.campulse;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Eduardo Coronado on 9/21/2016.
 */
public class FacebookApplication extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("FacebookApplication", "Application created");
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        // getHashKey();
    }

    // Get Facebook Hash Key
    private String getHashKey(){
        PackageInfo info;
        // TODO Hash key changes when in apk mode (production mode)
        try {
            info = getPackageManager().getPackageInfo("com.campulse.campulse", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
                return something;
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
        return null;
    }
    public FacebookApplication(){}
}
