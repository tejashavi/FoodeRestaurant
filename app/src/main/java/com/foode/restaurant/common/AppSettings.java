package com.foode.restaurant.common;

import android.app.Activity;



import java.util.List;

public final class AppSettings extends OSettings {
    public static final String PREFS_MAIN_FILE = "FoodeRestaurant";
    public static final String shopId = "shopId";


    public AppSettings(Activity mActivity) {
        super(mActivity);
    }
}
