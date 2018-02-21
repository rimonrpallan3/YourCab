package com.voyager.sayaradriver.splashscreen.presenter;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;


import com.google.gson.Gson;
import com.voyager.sayaradriver.common.Helper;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.splashscreen.view.ISplashView;

/**
 * Created by User on 8/28/2017.
 */

public class SplashPresenter implements IConnectionStatus{

    Context context;
    ISplashView splashView;
    Activity activity;

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;

    String userName;


    private int SPLASH_DISPLAY_LENGTH = 1000;

    public SplashPresenter(Context context, ISplashView splashView, Activity activity, SharedPreferences sharedPrefs, SharedPreferences.Editor editor) {
        this.activity = activity;
        this.context = context;
        this.splashView = splashView;
        this.sharedPrefs = sharedPrefs;
        this.editor = editor;
        userName = getUserGsonInSharedPrefrences();
    }

    public String getUserGsonInSharedPrefrences(){
        String username ="";
        Gson gson = new Gson();
        String json = sharedPrefs.getString("DriverUserModel", null);
        System.out.println("-----------addUserGsonInSharedPrefrences DriverUserModel"+json);
        if(json!=null){
            System.out.println("-----------addUserGsonInSharedPrefrences DriverUserModel"+json);
            DriverUserModel driverUserModel = gson.fromJson(json, DriverUserModel.class);
            username = driverUserModel.getUserName();
        }
        return username;
    }

    @Override
    public void load() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Helper.isLocationEnabled(context)) {
                    if(userName!=null && userName.length()>0){
                        splashView.moveToLanding();
                    }else{
                        splashView.moveToSignUpLogin();
                    }
                }else {
                    Helper.toEnabledLocation(context,activity);
                }
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
