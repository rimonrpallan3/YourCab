package com.voyager.user.yourcab.splashscreen.presenter;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;



import com.voyager.user.yourcab.common.Helper;
import com.voyager.user.yourcab.splashscreen.view.ISplashView;

/**
 * Created by User on 8/28/2017.
 */

public class SplashPresenter implements IConnectionStatus{


    Context context;
    ISplashView splashView;
    Activity activity;

    private int SPLASH_DISPLAY_LENGTH = 1000;

    public SplashPresenter(Context context, ISplashView splashView, Activity activity) {
        this.activity = activity;
        this.context = context;
        this.splashView = splashView;
    }

    @Override
    public void load() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Helper.isLocationEnabled(context)) {
                    splashView.moveToMainView();
                }else {
                    Helper.toEnabledLocation(context,activity);
                }
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
