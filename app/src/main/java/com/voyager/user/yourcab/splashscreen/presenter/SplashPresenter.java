package com.voyager.user.yourcab.splashscreen.presenter;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;


import com.voyager.user.yourcab.common.Helper;
import com.voyager.user.yourcab.splashscreen.view.ISplashView;

/**
 * Created by User on 8/28/2017.
 */

public class SplashPresenter implements IConnectionStatus{


    Context context;
    ISplashView splashView;

    private int SPLASH_DISPLAY_LENGTH = 1000;

    public SplashPresenter(Context context, ISplashView splashView) {
        this.context = context;
        this.splashView = splashView;
    }

    @Override
    public boolean isOnline() {
        System.out.println("Hello Online---------------");

        return false;
    }

    @Override
    public void load() {
        System.out.println("Hello load---------------");
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Helper.isLocationEnabled(context)) {
                    splashView.moveToMainView();
                }else {
                    Helper.toEnabledLocation(context);
                    if(Helper.isLocationEnabled(context)) {
                        splashView.moveToMainView();
                    }
                }
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
