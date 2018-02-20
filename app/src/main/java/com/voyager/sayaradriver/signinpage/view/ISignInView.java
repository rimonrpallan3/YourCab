package com.voyager.sayaradriver.signinpage.view;

import com.voyager.sayaradriver.signinpage.model.DriverUserModel;

/**
 * Created by User on 8/29/2017.
 */

public interface ISignInView {
    public void onClearText();
    public void onLoginResult(Boolean result, int code);
    public void onLoginResponse(Boolean result, int code);
    public void onSetProgressBarVisibility(int visibility);
    void sendPParcelableObj(DriverUserModel driverUserModel);
}
