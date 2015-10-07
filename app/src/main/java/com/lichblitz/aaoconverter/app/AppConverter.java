package com.lichblitz.aaoconverter.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by lichblitz on 4/09/15.
 */
public class AppConverter extends Application {

    /**
     * Application context.
     */

    private static Context context;



    /**
     * Return App global {@linkplain android.content.Context}.
     *
     * @return {@linkplain android.content.Context} global context.
     */

    public static Context getContext() {

        return context;

    }


    /**
     * Return a string for a given resource.
     *
     * @param resource String resource.
     * @return {@linkplain java.lang.String} for the given resource.
     */

    public static String getStringFromId(int resource) {

        return getContext().getString(resource);

    }


    /**
     * Get a resource for a given int resource.
     * @param resource int resource.
     * @return {@linkplain java.lang.Integer} for the given resource.
     */

    public static int getIntFromId(int resource) {

        return getContext().getResources().getInteger(resource);

    }


    /**
     * Get a color for a given int.
     * @param resource int resource.
     * @return {@linkplain java.lang.Integer} for the given resource.
     */
    public static int getColorFromId(int resource){
        return getContext().getResources().getColor(resource);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}