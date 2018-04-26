package com.anditer.goalit.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class deals with our shared preferences
 */

public class PrefManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    // shared pref mode
    private int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "goalit-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_LOGGEDIN = "IsLoggedIn";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public void setLogin(boolean isLoggedIn){
        editor.putBoolean(IS_LOGGEDIN, isLoggedIn);
        editor.commit();
    }

    public static void updateSignInPref(Context context, boolean isLoggedIn){
        PrefManager manager = new PrefManager(context);
        manager.setLogin(isLoggedIn);
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGGEDIN, false);
    }

}
