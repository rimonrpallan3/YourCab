package com.voyager.sayaradriver.signinpage.view;

/**
 * Created by User on 8/29/2017.
 */

public interface ISignInView {
    public void onClearText();
    public void onLoginResult(Boolean result, int code);
    public void onLoginResponse(Boolean result, int code);
    public void onSetProgressBarVisibility(int visibility);
}
