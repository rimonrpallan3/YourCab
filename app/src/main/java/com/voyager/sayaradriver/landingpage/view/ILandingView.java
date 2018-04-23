package com.voyager.sayaradriver.landingpage.view;

import com.voyager.sayaradriver.signinpage.model.DriverUserModel;

/**
 * Created by User on 8/29/2017.
 */

public interface ILandingView {
    void getOfflineOnlineState(String setText);
    void startService();
    void stopService();
    void addUserGsonInSharedPrefrences(DriverUserModel driverUserModel);
    //----------- This methods is used to connect with Map Fragment and MainActivity
    void hideViewsOnTripStartUp(int visibility);
}
