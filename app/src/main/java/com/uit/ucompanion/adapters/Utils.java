package com.uit.ucompanion.adapters;

import android.app.Activity;
import android.content.Intent;

import com.uit.ucompanion.R;
import com.uit.ucompanion.classes.TinyDB;

/**
 * Created by aYoe on 7/23/16.
 */
public class Utils {
    private static int sTheme;
    public final static int THEME_DEFAULT=0;
    public final static int THEME_BLUE=1;
    public final static int THEME_PINK=2;

    public static void changeToTheme(Activity activity,int theme){
        sTheme=theme;

        activity.finish();
        activity.startActivity(new Intent(activity,activity.getClass()));
    }

    public static void onActivityCreateSetTheme(Activity activity){
        TinyDB tinyDB=new TinyDB(activity);
        if(tinyDB.getInt("theme")==1)
            activity.setTheme(R.style.AppThemeBlue);
        else if(tinyDB.getInt("theme")==2)
            activity.setTheme(R.style.AppThemePink);
        else
            activity.setTheme(R.style.AppTheme);

    }
}
