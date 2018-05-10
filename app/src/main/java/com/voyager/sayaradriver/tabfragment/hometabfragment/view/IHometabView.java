package com.voyager.sayaradriver.tabfragment.hometabfragment.view;

import com.voyager.sayaradriver.tabfragment.hometabfragment.model.CurrentPlaceDetails;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.geogetpath.Route;

import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 14-Mar-18.
 */

public interface IHometabView {
    void acceptTrip();
    void driverStartTrip();
    void rejectTrip();
    void stopedTrip();
    void hideViewsOnTrip(int visibility);
    void setRoutesToCustomer(List<List<HashMap<String, String>>> route, List<Route> routes, String tripDist);
    void setRoutesToDestination(List<List<HashMap<String, String>>> route, List<Route> routes, String tripDist);
    void highLikeHoodCurrentPlace(CurrentPlaceDetails maxCurrentPlaceDetails);
}
