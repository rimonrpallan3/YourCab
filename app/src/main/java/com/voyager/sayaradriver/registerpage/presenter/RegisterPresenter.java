package com.voyager.sayaradriver.registerpage.presenter;

import com.voyager.sayaradriver.registerpage.model.IUserValidate;
import com.voyager.sayaradriver.registerpage.model.UserDetails;
import com.voyager.sayaradriver.registerpage.view.IRegisterView;

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
        System.out.println("FName : "+FName+" LName : "+LName+" phno : "+phno+" city : "+city+" CPR : "+CPR);
        Boolean isLoginSuccess = true;
        final int code = user.validateUserDetails(FName,LName,phno,city,CPR);
        if (code!=0) {
            isLoginSuccess = false;
        }else {
            this.FName = FName;
            this.LName = LName;
            this.phno = phno;
            this.city = city;
            this.CPR = CPR;
        }
        final Boolean result = isLoginSuccess;
        iRegisterView.onRegister(result, code);
    }

    private void initUser(){
        user = new UserDetails(FName,LName,phno,city,CPR);
    }
}
