package com.voyager.user.yourcab.registerpage.presenter;

import com.voyager.user.yourcab.registerpage.model.IUserValidate;
import com.voyager.user.yourcab.registerpage.model.UserDetails;
import com.voyager.user.yourcab.registerpage.view.IRegisterView;
import com.voyager.user.yourcab.signinpage.model.IUser;
import com.voyager.user.yourcab.signinpage.model.UserModel;
import com.voyager.user.yourcab.signinpage.view.ISignInView;

/**
 * Created by User on 8/29/2017.
 */

public class RegisterPresenter implements IRegisterFetcher{

    IRegisterView iRegisterView;
    IUserValidate user;
    String FName;
    String LName;
    String phno;
    String city;
    String CPR;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView =iRegisterView;
        initUser();
    }

    @Override
    public void doRegister(String FName, String LName, String phno, String city, String CPR) {
        this.FName = FName;
        this.LName = LName;
        this.phno = phno;
        this.city = city;
        this.CPR = CPR;
        Boolean isLoginSuccess = true;
        final int code = user.validateUserDetails(FName,LName,phno,city,CPR);
        if (code!=0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        iRegisterView.onRegister(result, code);

    }

    private void initUser(){
        user = new UserDetails(FName,LName,phno,city,CPR);
    }
}
