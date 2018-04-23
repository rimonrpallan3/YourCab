package com.voyager.sayaradriver.tabfragment.hometabfragment.view;

import com.voyager.sayaradriver.tabfragment.hometabfragment.model.geogetpath.Route;

import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 14-Mar-18.
 */

public interface IHometabView {
    void acceptTrip();
    void rejectTrip();
    void hideViewsOnTrip(int visibility);
    void setRoutesToCustomer(List<List<HashMap<String, String>>> route, List<Route> routes, String tripDist);
}
