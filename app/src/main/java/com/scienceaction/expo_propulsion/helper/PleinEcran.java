package com.scienceaction.expo_propulsion.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by gab on 17/08/16.
 */
public final class PleinEcran {

     public static void pleinEcran(Context context) {
        View decorView = ((Activity)context).getWindow().getDecorView();
            decorView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

}
