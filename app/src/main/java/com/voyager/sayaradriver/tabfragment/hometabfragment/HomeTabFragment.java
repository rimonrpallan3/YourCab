package com.voyager.sayaradriver.tabfragment.hometabfragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.costom.CircleImageView;
import com.voyager.sayaradriver.landingpage.view.ILandingView;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabPresenter.HomeTabPresenter;
import com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabPresenter.IHomeTabPresenter;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.FCMDetials;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.MapDetails;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.geogetpath.Route;
import com.voyager.sayaradriver.tabfragment.hometabfragment.view.IHometabView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rimon on 22/4/16.
 */
public class HomeTabFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, IHometabView {

    List<MapDetails> mapDetailsList;
    private GoogleMap googleMap;
    private MapView mMapView;
    LocationManager locationManager;
    String mprovider;
    private LatLng CURRENTLOCATION = null;
    DriverUserModel driverUserModel;
    /**
     * Provides access to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;
    protected Location mLastKnownLocation;
    private Marker mCLocation;
    LocationRequest mLocationRequest;

    private static final String TAG = HomeTabFragment.class.getSimpleName();

    double lat = 26.2285;
    double log = 50.5860;

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    View rootView;
    Activity activity;

    FrameLayout suddenTrip;
    Button tripAccept;
    Button tripReject;
    TextView tripTimeOut;
    TextView tripUser;
    TextView tripStartOrgin;
    TextView tripEndDestin;
    TextView tripDistance;
    TextView tripCostFair;
    TextView tripPaymentMethod;
    String fcmAvliable = "";
    FCMDetials fcmDetials;
    IHomeTabPresenter iHomeTabPresenter;

    String fcmPush = "";
    Bundle bundle;

    ILandingView iLandingView;

    @BindView(R.id.driverHeaderLayout)
    LinearLayout driverHeaderLayout;
    @BindView(R.id.driverBodyLayout)
    LinearLayout driverBodyLayout;
    @BindView(R.id.tripSupportCall)
    LinearLayout tripSupportCall;
    @BindView(R.id.tripCustomerCall)
    LinearLayout tripCustomerCall;
    @BindView(R.id.tripCancel)
    LinearLayout tripCancel;
    @BindView(R.id.driverCircleImageView)
    CircleImageView driverCircleImageView;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userAddress)
    TextView userAddress;
    @BindView(R.id.tripStartOrginStartUp)
    TextView tripStartOrginStartUp;
    @BindView(R.id.tripEndDestinStartUp)
    TextView tripEndDestinStartUp;
    @BindView(R.id.tripPayType)
    TextView tripPayType;
    @BindView(R.id.tripFare)
    TextView tripFare;
    @BindView(R.id.tripDist)
    TextView tripDist;
    @BindView(R.id.onTripStartUpLayout)
    FrameLayout onTripStartUpLayout;
    @BindView(R.id.startTrip)
    Button startTrip;
    String pickLat ="";
    String picklng ="";

    Boolean isClicked = true;

    String ApiKey = "";

    public HomeTabFragment(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.home_tab_fragment, container, false);
        this.rootView = rootView;
        ButterKnife.bind(this, rootView);
        System.out.println("HomeTabFragment");

        ApiKey = getString(R.string.place_api_key);
        bundle = this.getArguments();

        mMapView = rootView.findViewById(R.id.map);
        tripAccept = (Button) rootView.findViewById(R.id.tripAccept);
        tripReject = (Button) rootView.findViewById(R.id.tripReject);
        suddenTrip = (FrameLayout) rootView.findViewById(R.id.suddenTrip);
        tripTimeOut = (TextView) rootView.findViewById(R.id.tripTimeOut);
        tripUser = (TextView) rootView.findViewById(R.id.tripUser);
        tripStartOrgin = (TextView) rootView.findViewById(R.id.tripStartOrgin);
        tripEndDestin = (TextView) rootView.findViewById(R.id.tripEndDestin);
        tripDistance = (TextView) rootView.findViewById(R.id.tripDistance);
        tripCostFair = (TextView) rootView.findViewById(R.id.tripCostFair);
        tripPaymentMethod = (TextView) rootView.findViewById(R.id.tripPaymentMethod);
        onTripStartUpLayout = (FrameLayout) rootView.findViewById(R.id.onTripStartUpLayout);
        driverHeaderLayout = (LinearLayout) rootView.findViewById(R.id.driverHeaderLayout);
        driverBodyLayout = (LinearLayout) rootView.findViewById(R.id.driverBodyLayout);
        tripSupportCall = (LinearLayout) rootView.findViewById(R.id.tripSupportCall);
        tripCustomerCall = (LinearLayout) rootView.findViewById(R.id.tripCustomerCall);
        tripCancel = (LinearLayout) rootView.findViewById(R.id.tripCancel);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        mprovider = locationManager.getBestProvider(criteria, false);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        tripAccept.setOnClickListener(this);
        tripReject.setOnClickListener(this);
        iHomeTabPresenter = new HomeTabPresenter(this);

        if (bundle != null) {
            try {
                driverUserModel = bundle.getParcelable("DriverUserModel");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fcmDetials = bundle.getParcelable("FCMDetials");
                fcmPush = bundle.getString("fcmPush");
                System.out.println(" ----------- HomeTabFragment fcmPush" + fcmPush);
                if (fcmPush != null && fcmPush.length() > 1 && fcmDetials.getUserName().length() > 0) {
                    tripUser.setText(fcmDetials.getUserName());
                    tripStartOrgin.setText(fcmDetials.getPickupAddress());
                    tripEndDestin.setText(fcmDetials.getDropAddress());
                    tripDistance.setText(fcmDetials.getDistance());
                    tripCostFair.setText(fcmDetials.getFare());
                    tripPaymentMethod.setText(fcmDetials.getPayType());
                    userName.setText(fcmDetials.getUserName());
                    Picasso.with(getContext()).load(fcmDetials.getUserPhoto()).error(R.drawable.profile).into(driverCircleImageView);
                    userAddress.setText(fcmDetials.getUserCity() + "," + fcmDetials.getUserCountry());
                    tripStartOrginStartUp.setText(fcmDetials.getPickupAddress());
                    tripEndDestinStartUp.setText(fcmDetials.getDropAddress());
                    tripPayType.setText(fcmDetials.getDropAddress());
                    tripFare.setText(fcmDetials.getFare());
                    tripDist.setText(fcmDetials.getDistance());
                    String[] pickUpLoc = fcmDetials.getPickupLocation().toString().split(",");
                    for (int x=0; x<pickUpLoc.length; x++) {
                        System.out.println("x : "+x+" = "+pickUpLoc[x]);
                        pickLat = pickUpLoc[0];
                        picklng = pickUpLoc[1];
                    }
                    suddenTrip.setVisibility(View.VISIBLE);
                } else if (fcmPush != null && fcmPush.length() > 0 && fcmDetials.getTripStatus().equals("gone")) {
                    if (suddenTrip.getVisibility() == View.VISIBLE) {
                        suddenTrip.setVisibility(View.GONE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            if (driverUserModel != null) {
                String jsonString = gson.toJson(driverUserModel);
                System.out.println("-----------HomeTabFragment DriverUserModel" + jsonString);
            }
        }

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());

        return rootView;
    }


    /**
     * This method creates an ArrayList that has Church Notification model class objects
     */
    private List<MapDetails> initializeData() {
        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        mapDetailsList = new ArrayList<>();
        mapDetailsList.add(new MapDetails());
        return mapDetailsList;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        System.out.println("GoogleMap_CTL_lat-------" + lat + ",  CTL_log--------" + log);
        googleMap = map;
        googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        getActivity(), R.raw.map_style));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        map.addMarker(new MarkerOptions().position(new LatLng(lat, log)).title("Marker"));
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(lat, log));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
        if (fcmAvliable != null) {
            // suddenTrip.setVisibility(View.VISIBLE);
        }
        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }

           locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 5000, 0,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        lat=location.getLatitude();
                        log=location.getLongitude();
                        System.out.println("NETWORK_PROVIDER_CTL_lat-------" + lat + ",  CTL_log--------" + log);
                        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(lat, log));
                        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
                        googleMap.moveCamera(center);
                        googleMap.animateCamera(zoom);
                    }
                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }
                    @Override
                    public void onProviderEnabled(String provider) {
                    }
                    @Override
                    public void onProviderDisabled(String provider) {
                    }
                });

        //mMapView.getMapAsync(this);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        Snackbar.make(rootView.findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
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
     * Return the current state of the permissions needed.
     */
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            iLandingView = (ILandingView) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
        if (!checkPermissions()) {
            requestPermissions();
        } else {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tripAccept:
                suddenTrip.setVisibility(View.GONE);
                onTripStartUpLayout.setVisibility(View.VISIBLE);
                iHomeTabPresenter.acceptTrip(driverUserModel.driverId, fcmDetials.getTripId());
                iHomeTabPresenter.suddenTripStart();
                break;
            case R.id.tripReject:
                iHomeTabPresenter.rejectTrip(driverUserModel.driverId, fcmDetials.getTripId());
                break;
            case R.id.driverHeaderLayout:
                if(isClicked){
                    driverBodyLayout.setVisibility(View.VISIBLE);
                    isClicked = false;
                }else if(!isClicked) {
                    driverBodyLayout.setVisibility(View.GONE);
                    isClicked = true;
                }
                break;
        }
    }

    /**
     * Zooms a Route (given a List of LalLng) at the greatest possible zoom level.
     *
     * @param googleMap: instance of GoogleMap
     * @param lstLatLngRoute: list of LatLng forming Route
     */
    public void zoomRoute(GoogleMap googleMap, List<LatLng> lstLatLngRoute) {

        if (googleMap == null || lstLatLngRoute == null || lstLatLngRoute.isEmpty()) return;

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        for (LatLng latLngPoint : lstLatLngRoute)
            boundsBuilder.include(latLngPoint);

        int routePadding = 300;
        LatLngBounds latLngBounds = boundsBuilder.build();

        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding));
        //googleMap.setPadding(0, 0, 0, 30);
    }


    private Bitmap getMarkerBitmapFromView(String locationNAme) {

        View customMarkerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
        TextView locationName = (TextView) customMarkerView.findViewById(R.id.locationName);
        locationName.setText(locationNAme);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    @Override
    public void acceptTrip() {
        iHomeTabPresenter.hideTripStartViews(View.GONE);
        bundle.putString("fcmPush", null);
        iHomeTabPresenter.suddenTripStart();
        onTripStartUpLayout.setVisibility(View.VISIBLE);
        String currentLat = String.valueOf(lat);
        String currentLng = String.valueOf(log);
        iHomeTabPresenter.getToCustomerDirection(currentLat,currentLng,pickLat,picklng,false,ApiKey);
    }

    @Override
    public void rejectTrip() {
        suddenTrip.setVisibility(View.GONE);
        bundle.putString("fcmPush", null);
    }

    @Override
    public void hideViewsOnTrip(int visibility) {
        suddenTrip.setVisibility(visibility);
        iLandingView.hideViewsOnTripStartUp(visibility);
    }

    @OnClick(R.id.startTrip)
    public void startTripBtnClick(){
        //startTrip.

    }

    @Override
    public void setRoutesToCustomer(List<List<HashMap<String, String>>> route, List<Route> routes, String tripDist) {
        double pickUpLatD = Double.parseDouble(pickLat);
        double pickUpLngD = Double.parseDouble(picklng);

        if (lat > 0.0 && log > 0.0 && pickUpLatD > 0.0 && pickUpLngD > 0.0) {
            System.out.println("MapFragmentView---IF");
            if (mMapView != null) {
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, log))
                        .title("From"))
                        .setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView("Current Location")));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(pickUpLatD, pickUpLngD))
                        .title("To"))
                        .setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(fcmDetials.getPickupAddress())));
                ArrayList<LatLng> points = null;
                PolylineOptions polyLineOptions = null;
                // traversing through routes
                double dist = 0;
                Location srcLoc = new Location("");
                Location destLoc = new Location("");
                for (int i = 0; i < routes.size(); i++) {
                    points = new ArrayList<LatLng>();
                    polyLineOptions = new PolylineOptions();
                    List<HashMap<String, String>> path = route.get(i);
                    for (int j = 0; j < path.size(); j++) {
                        HashMap<String, String> point = path.get(j);
                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        if (destLoc.getLatitude() > 0) {
                            destLoc.setLatitude(srcLoc.getLatitude());
                            destLoc.setLongitude(srcLoc.getLongitude());
                        } else {
                            destLoc.setLatitude(lat);
                            destLoc.setLongitude(lng);
                        }
                        srcLoc.setLatitude(lat);
                        srcLoc.setLongitude(lng);
                        dist = dist + destLoc.distanceTo(srcLoc);
                        LatLng position = new LatLng(lat, lng);
                        points.add(position);
                    }
                    zoomRoute(googleMap,points);
                    polyLineOptions.addAll(points);
                    polyLineOptions.width(10);
                    polyLineOptions.color(Color.DKGRAY);
                }
                onTripStartUpLayout.setVisibility(View.VISIBLE);
                if (polyLineOptions != null) {
                    System.out.println("MapFragmentView---polyLineOptions --IF");
                    googleMap.addPolyline(polyLineOptions);
                } else {
                    Toast.makeText(getActivity(), "Could not find path", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}