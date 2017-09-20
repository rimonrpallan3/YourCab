package com.voyager.sayaradriver.otppage.view;

/**
 * Created by User on 8/30/2017.
 */

public interface IOTPView {
    void onSubmit(Boolean result, int code);
    void moveToTermsAndConductionPage();

}
