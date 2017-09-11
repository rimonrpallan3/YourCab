package com.voyager.user.yourcab.signinpage.presenter;

import android.os.Handler;
import android.os.Looper;

import com.voyager.user.yourcab.signinpage.model.IUser;
import com.voyager.user.yourcab.signinpage.model.UserModel;
import com.voyager.user.yourcab.signinpage.view.ISignInView;

/**
 * Created by User on 8/29/2017.
 */

public class SignInPresenter implements ILoginPresenter {

    ISignInView iSignInView;
    IUser user;
    Handler handler;

    public SignInPresenter(ISignInView iSignInView) {
        this.iSignInView = iSignInView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void clear() {
        iSignInView.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {
        Boolean isLoginSuccess = true;
        final int code = user.checkUserValidity(name,passwd);
        if (code!=0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iSignInView.onLoginResult(result, code);
            }
        }, 5000);

    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iSignInView.onSetProgressBarVisibility(visiblity);
    }

    private void initUser(){
        user = new UserModel("123","123");
    }
}
