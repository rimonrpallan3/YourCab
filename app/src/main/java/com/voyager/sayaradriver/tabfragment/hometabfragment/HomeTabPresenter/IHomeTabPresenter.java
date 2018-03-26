package com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabPresenter;

/**
 * Created by User on 14-Mar-18.
 */

public interface IHomeTabPresenter {
    void acceptTrip(int driverId,String tripId);
    void rejectTrip(int driverId,String tripId);
}
