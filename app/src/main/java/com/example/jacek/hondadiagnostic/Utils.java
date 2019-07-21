package com.example.jacek.hondadiagnostic;

/**
 * Created by Jacek on 2018-07-30.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class Utils {
    private static int sTheme;
    public final static int ECO_THEME = 0;
    public final static int R_THEME = 1;


    public static void setTheme(int t){
        sTheme = t;
    }
    /** * Set the theme of the Activity, and restart it by creating a new Activity of the same type. */
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    } /** Set the theme of the activity, according to the configuration. */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {

            case ECO_THEME: activity.setTheme(R.style.Eco_theme);

        break;

        case R_THEME: activity.setTheme(R.style.R_theme);
        break;
        } } }


