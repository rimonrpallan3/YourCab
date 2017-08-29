package com.voyager.user.yourcab.splashscreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.voyager.user.yourcab.LoginSignupPage.MainActivity;
import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.common.Helper;
import com.voyager.user.yourcab.splashscreen.presenter.SplashPresenter;
import com.voyager.user.yourcab.splashscreen.view.ISplashView;


/**
 * Created by User on 8/23/2017.
 */

public class SplashScreen extends AppCompatActivity implements ISplashView{

    private SplashPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        mPresenter = new SplashPresenter(this,this,this);
        mPresenter.load();
    }

    @Override
    public void moveToMainView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Helper.REQUEST_LOCATION_CHECK_SETTINGS) {
            mPresenter.load();
        }
    }
}