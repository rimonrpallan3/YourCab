package com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabPresenter;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.CurrentPlaceDetails;
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
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    List<CurrentPlaceDetails> currentPlaceDetailsList = new ArrayList<>();
    float maxLikeHood = 0;
    CurrentPlaceDetails maxCurrentPlaceDetails;
    Activity activity;
    String TAG;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    public HomeTabPresenter(IHometabView iHometabView,Activity activity,String TAG) {
        this.iHometabView =iHometabView;
        this.activity = activity;
        this.TAG = TAG;
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
                System.out.println("HomeTabPresenter----- acceptTrip onSuccess- ErrorMsg: "+tripDetails.getError_msg()+
                        ", Error :"+tripDetails.getError());
            }

            @Override
            public void onFailure(Call<TripDetails> call, Throwable t) {
                //Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("HomeTabPresenter----- acceptTrip onFailure: "+t.getMessage());
                Log.e("LandingPresenter uploadProfileName error:", t.getMessage());
            }
        });

    }

    @Override
    public void startTrip(int driverId, Integer tripId) {
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);

        Call<TripDetails> call = webServices.driverStartTrip(driverId, tripId);
        call.enqueue(new Callback<TripDetails>() {
            @Override
            public void onResponse(Call<TripDetails> call,
                                   Response<TripDetails> response) {

                tripDetails =response.body();
                iHometabView.driverStartTrip();
                System.out.println("HomeTabPresenter----- startTrip onSuccess- ErrorMsg: "+tripDetails.getError_msg()+
                        ", Error :"+tripDetails.getError());
            }

            @Override
            public void onFailure(Call<TripDetails> call, Throwable t) {
                //Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("HomeTabPresenter----- startTrip onFailure: "+t.getMessage());
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
    public void endTrip(int driverId, Integer tripId) {
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);

        Call<TripDetails> call = webServices.driverStopTrip(driverId, tripId);
        call.enqueue(new Callback<TripDetails>() {
            @Override
            public void onResponse(Call<TripDetails> call,
                                   Response<TripDetails> response) {

                tripDetails =response.body();
                iHometabView.stopedTrip();
                System.out.println("HomeTabPresenter----- endTrip onSuccess- ErrorMsg: "+tripDetails.getError_msg()+
                        ", Error :"+tripDetails.getError());
            }

            @Override
            public void onFailure(Call<TripDetails> call, Throwable t) {
                //Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("HomeTabPresenter----- endTrip onFailure: "+t.getMessage());
                Log.e("LandingPresenter uploadProfileName error:", t.getMessage());
            }
        });
    }


    @Override
    public void hideTripStartViews(int visibility) {
        iHometabView.hideViewsOnTrip(visibility);
    }

    @Override
    public void getCurrentLocDetails() {
        if (!checkPermissions()) {
            requestPermissions();
        } else {
            maxCurrentPlaceDetails = new CurrentPlaceDetails();

            // Construct a GeoDataClient.
            mGeoDataClient = Places.getGeoDataClient(activity, null);

            // Construct a PlaceDetectionClient.
            mPlaceDetectionClient = Places.getPlaceDetectionClient(activity, null);
            try{
                int permissionState = ActivityCompat.checkSelfPermission(activity.getApplicationContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION);
                Task<PlaceLikelihoodBufferResponse> placeResult = mPlaceDetectionClient.getCurrentPlace(null);
                placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                        PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                        for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                            CurrentPlaceDetails currentPlaceDetails = new CurrentPlaceDetails();
                            Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                                    placeLikelihood.getPlace().getName(),
                                    placeLikelihood.getLikelihood()));
                            currentPlaceDetails.setLikehood(placeLikelihood.getLikelihood());
                            currentPlaceDetails.setLat(String.valueOf(placeLikelihood.getPlace().getLatLng().latitude));
                            currentPlaceDetails.setLng(String.valueOf(placeLikelihood.getPlace().getLatLng().longitude));
                            currentPlaceDetails.setPlaceid(placeLikelihood.getPlace().getId());
                            currentPlaceDetails.setPlaceName(placeLikelihood.getPlace().getName().toString());
                            System.out.println("Name : "+placeLikelihood.getPlace().getName()
                                    +", Like hood : "+placeLikelihood.getLikelihood()
                                    +", LatLog : "+placeLikelihood.getPlace().getLatLng());
                            currentPlaceDetailsList.add(currentPlaceDetails);

                        }
                        likelyPlaces.release();
                        Gson gson = new Gson();
                        String json = gson.toJson(currentPlaceDetailsList);
                        System.out.println("-----------MapFragmentPresenter PlaceLikelihoodBufferResponse currentPlaceDetailsList : " + json);
                        for(int i=0;i<currentPlaceDetailsList.size();i++){
                            CurrentPlaceDetails currentPlaceDetails = currentPlaceDetailsList.get(i);
                            float mostLikeHood= currentPlaceDetails.getLikehood();
                            System.out.println("-----------MapFragmentPresenter mostLikeHood : " + mostLikeHood);
                            if(maxLikeHood<mostLikeHood){
                                maxLikeHood = mostLikeHood;
                                maxCurrentPlaceDetails = currentPlaceDetails;
                            }

                        }
                        iHometabView.highLikeHoodCurrentPlace(maxCurrentPlaceDetails);
                        String json2 = gson.toJson(maxCurrentPlaceDetails);
                        System.out.println("-----------MapFragmentPresenter PlaceLikelihoodBufferResponse CurrentPlaceDetails : " + json2);
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("-----------MapFragmentPresenter PlaceLikelihoodBufferResponse Error  : " + e.getMessage().toString());
            }
        }
    }

    @Override
    public void getToCustomerDirection(String originLat, String originLng, String destinationLat, String destinationLng, Boolean sensor, String ApiKey) {
        System.out.println("HomeTabFragment acceptTrip currentLat: "+ originLat +
                ",currentLng : "+ originLng +",pickLat :"+destinationLat+",picklng: "+destinationLng);
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

    @Override
    public void startOnGoingTrip(String originLat, String originLng, String destinationLat, String destinationLng, Boolean sensor, String ApiKey) {
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
                iHometabView.setRoutesToDestination(route,routes,tripDist);
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



    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");

            showSnackbar(R.string.permission_rationale, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(activity,
                                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    });

        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    /**
     * Shows a {@link Snackbar}.
     *
     * @param mainTextStringId The id for the string resource for the Snackbar text.
     * @param actionStringId   The text of the action item.
     * @param listener         The listener associated with the Snackbar action.
     */
    private void showSnackbar(final int mainTextStringId, final int actionStringId,
                              View.OnClickListener listener) {
        Snackbar.make(activity.findViewById(android.R.id.content),
                activity.getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(activity.getString(actionStringId), listener).show();
    }


    /**
     * Return the current state of the permissions needed.
     */
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }
}
