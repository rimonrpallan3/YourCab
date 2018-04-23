package com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabPresenter;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.TripDetails;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.geogetpath.Distance;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.geogetpath.GetPaths;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.geogetpath.Leg;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.geogetpath.Route;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.geogetpath.Step;
import com.voyager.sayaradriver.tabfragment.hometabfragment.view.IHometabView;
import com.voyager.sayaradriver.webservices.ApiClient;
import com.voyager.sayaradriver.webservices.WebServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 14-Mar-18.
 */

public class HomeTabPresenter implements IHomeTabPresenter{

    IHometabView iHometabView;
    TripDetails tripDetails;

    String tripDist ="";
    String driverLat = "";
    String driverlng = "";
    String pickUpLat = "";
    String pickUplng = "";

    public HomeTabPresenter(IHometabView iHometabView) {
        this.iHometabView =iHometabView;
    }


    @Override
    public void acceptTrip(int driverId,Integer tripId) {
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);

        Call<TripDetails> call = webServices.driverAcceptTrip(driverId, tripId);
        call.enqueue(new Callback<TripDetails>() {
            @Override
            public void onResponse(Call<TripDetails> call,
                                   Response<TripDetails> response) {

                tripDetails =response.body();
                iHometabView.acceptTrip();
                System.out.println("HomeTabPresenter----- rejectTrip onSuccess- ErrorMsg: "+tripDetails.getError_msg()+
                        ", Error :"+tripDetails.getError());
            }

            @Override
            public void onFailure(Call<TripDetails> call, Throwable t) {
                //Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("HomeTabPresenter----- rejectTrip onFailure: "+t.getMessage());
                Log.e("LandingPresenter uploadProfileName error:", t.getMessage());
            }
        });

    }

    @Override
    public void rejectTrip(int driverId,Integer tripId) {
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);

        Call<TripDetails> call = webServices.driverRejectTrip(driverId, tripId);
        call.enqueue(new Callback<TripDetails>() {
            @Override
            public void onResponse(Call<TripDetails> call,
                                   Response<TripDetails> response) {

                tripDetails =response.body();
                iHometabView.rejectTrip();
                System.out.println("HomeTabPresenter----- rejectTrip onSuccess- ErrorMsg: "+tripDetails.getError_msg()+
                        ", Error :"+tripDetails.getError());
            }

            @Override
            public void onFailure(Call<TripDetails> call, Throwable t) {
                //Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("HomeTabPresenter----- rejectTrip onFailure: "+t.getMessage());
                Log.e("LandingPresenter uploadProfileName error:", t.getMessage());
            }
        });
    }

    @Override
    public void suddenTripStart() {

    }

    @Override
    public void hideTripStartViews(int visibility) {
        iHometabView.hideViewsOnTrip(visibility);
    }

    @Override
    public void getToCustomerDirection(String originLat, String originLng, String destinationLat, String destinationLng, Boolean sensor, String ApiKey) {
        Retrofit retrofit = new ApiClient().getRetrofitClientPath();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<GetPaths> call = webServices.getPaths(originLat + "," + originLng, destinationLat + "," + destinationLng, sensor,ApiKey);
        call.enqueue(new Callback<GetPaths>() {
            @Override
            public void onResponse(Call<GetPaths> call, Response<GetPaths> response) {
                GetPaths getPaths = response.body();
                List<List<HashMap<String, String>>> route = new ArrayList<List<HashMap<String, String>>>();
                List<Route> routes = getPaths.getRoutes();
                for (int i = 0; i < routes.size(); i++) {
                    List<HashMap<String, String>> path = new ArrayList<HashMap<String, String>>();
                    List<Leg> legs = routes.get(i).getLegs();
                    Distance distance = legs.get(0).getDistance();
                    tripDist = distance.getText();
                    for (int j = 0; j < legs.size(); j++) {
                        List<Step> steps = legs.get(j).getSteps();
                        for (int k = 0; k < steps.size(); k++) {
                            String polyline = steps.get(k).getPolyline().getPoints();
                            List<LatLng> latLngs = decodePoly(polyline);
                            for (int l = 0; l < latLngs.size(); l++) {
                                HashMap<String, String> hm = new HashMap<String, String>();
                                hm.put("lat",
                                        Double.toString(((LatLng) latLngs.get(l)).latitude));
                                hm.put("lng",
                                        Double.toString(((LatLng) latLngs.get(l)).longitude));
                                path.add(hm);
                            }
                        }
                    }
                    route.add(path);
                }
                iHometabView.setRoutesToCustomer(route,routes,tripDist);
            }

            @Override
            public void onFailure(Call<GetPaths> call, Throwable t) {

                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }
}
