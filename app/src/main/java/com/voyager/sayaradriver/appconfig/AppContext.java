package com.voyager.sayaradriver.appconfig;


import com.voyager.sayaradriver.BuildConfig;

/**
 * Created by Amine on 9/29/2016.
 */

public class AppContext {


    public static boolean DEBUG         = AppConfig.APP_DEBUG;
    public static boolean ADS           = false;
    public static boolean USER_TRACKING = false;

    public static String PATH_PROVIDER = BuildConfig.APPLICATION_ID+".fileprovider";

    public static class Ads{
        public static boolean ADS_IN_PEOPLE_LIST        =   false;
        public static boolean ADS_IN_PLACE_ACTIVITY     =   false;
        public static boolean ADS_IN_PLACES_LIST        =   false;
        public static boolean ADS_IN_POSTS_LIST         =   false;
    }



}
