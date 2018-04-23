package com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabPresenter;

/**
 * Created by User on 14-Mar-18.
 */

public interface IHomeTabPresenter {
    void acceptTrip(int driverId,Integer tripId);
    void rejectTrip(int driverId,Integer tripId);
    void suddenTripStart();
    void hideTripStartViews(int visibility);
    void getToCustomerDirection(final String originLat,final String originLng,  String destinationLat,String destinationLng, Boolean sensor, String ApiKey);
    void startOnGoingTrip(final String originLat,final String originLng,  String destinationLat,String destinationLng, Boolean sensor, String ApiKey);
}
