package com.example.darshan.isd.utils;

import android.content.Context;
import android.content.pm.PackageManager;

public class utils {

    public static String FACEBOOK_URL = "https://www.facebook.com/isdmdancestudio";
    public static String FACEBOOK_PAGE_ID = "1419059588174599";

    /*
    This method opens the facebook page of ISD. Opens in the app if facebook app is already installed, else open the page in the mobile
    browser.
     */

    public static String openFaceBookUrl(Context context) {

        PackageManager packageManager = context.getPackageManager();

        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;

            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else {
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }
}
