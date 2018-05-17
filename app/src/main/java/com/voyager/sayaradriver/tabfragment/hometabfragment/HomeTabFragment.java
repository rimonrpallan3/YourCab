package com.voyager.sayaradriver.tabfragment.hometabfragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
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
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.picasso.Picasso;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.costom.CircleImageView;
import com.voyager.sayaradriver.landingpage.view.ILandingView;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabPresenter.HomeTabPresenter;
import com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabPresenter.IHomeTabPresenter;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.CurrentPlaceDetails;
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

import static com.voyager.sayaradriver.common.Helper.REQUEST_PHONE_CUSTOMER_CALL;
import static com.voyager.sayaradriver.common.Helper.REQUEST_PHONE_SUPPORT_CALL;

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
    private Marker mCLocation;
    LocationRequest mLocationRequest;

    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

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
    String currentLng = "";
    String currentLat = "";

    @BindView(R.id.driverHeaderLayout)
    LinearLayout driverHeaderLayout;
    @BindView(R.id.driverBodyLayout)
    LinearLayout driverBodyLayout;
    @BindView(R.id.tripSupportCall)
    LinearLayout tripSupportCall;
    @BindView(R.id.tripCustomerCall)
    LinearLayout tripCustomerCall;
    @BindView(R.id.tripSupport)
    LinearLayout tripSupport;
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
    LinearLayout onTripStartUpLayout;
    @BindView(R.id.startTrip)
    Button startTrip;
    @BindView(R.id.stopTrip)
    Button stopTrip;
    @BindView(R.id.onGoingTripLayout)
    LinearLayout onGoingTripLayout;
    @BindView(R.id.imgOnGoingSupport)
    ImageView imgOnGoingSupport;
    @BindView(R.id.imgCallCustomer)
    ImageView imgCallCustomer;
    @BindView(R.id.imgCallSupport)
    ImageView imgCallSupport;
    String pickLat ="";
    String picklng ="";
    String dropLat ="";
    String droplng ="";

    Boolean isClicked = true;

    String ApiKey = "";
    private final LatLng mDefaultLocation = new LatLng(lat, log);
    private Location mLastKnownLocation;
    // The entry points to the Places API.
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private boolean mLocationPermissionGranted;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    CurrentPlaceDetails maxCurrentPlaceDetails;

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

        ApiKey = getString(R.string.map_api_key);
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
        onTripStartUpLayout = (LinearLayout) rootView.findViewById(R.id.onTripStartUpLayout);
        onGoingTripLayout = (LinearLayout) rootView.findViewById(R.id.onGoingTripLayout);
        driverHeaderLayout = (LinearLayout) rootView.findViewById(R.id.driverHeaderLayout);
        driverBodyLayout = (LinearLayout) rootView.findViewById(R.id.driverBodyLayout);
        tripSupportCall = (LinearLayout) rootView.findViewById(R.id.tripSupportCall);
        tripCustomerCall = (LinearLayout) rootView.findViewById(R.id.tripCustomerCall);
        tripSupport = (LinearLayout) rootView.findViewById(R.id.tripSupport);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        mprovider = locationManager.getBestProvider(criteria, false);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        tripAccept.setOnClickListener(this);
        tripReject.setOnClickListener(this);
        onGoingTripLayout.setOnClickListener(this);
        driverHeaderLayout.setOnClickListener(this);
        driverBodyLayout.setOnClickListener(this);
        tripSupportCall.setOnClickListener(this);
        tripCustomerCall.setOnClickListener(this);
        tripSupport.setOnClickListener(this);
        Gson gson = new Gson();
        iHomeTabPresenter = new HomeTabPresenter(this,getActivity(),TAG);

        final Drawable callIcon = new IconicsDrawable(getActivity())
                .icon(CommunityMaterial.Icon.cmd_phone)
                .color(ResourcesCompat.getColor(getResources(),R.color.iconColor,null))
                .sizeDp(10);
        final Drawable cancelIcon = new IconicsDrawable(getActivity())
                .icon(CommunityMaterial.Icon.cmd_cancel)
                .color(ResourcesCompat.getColor(getResources(),R.color.red,null))
                .sizeDp(10);

        imgOnGoingSupport.setImageDrawable(callIcon);
        imgCallCustomer.setImageDrawable(callIcon);
        imgCallSupport.setImageDrawable(callIcon);
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(getActivity(), null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(getActivity(), null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        if (bundle != null) {
            try {
                driverUserModel = bundle.getParcelable("DriverUserModel");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fcmDetials = bundle.getParcelable("FCMDetials");
                fcmPush = bundle.getString("fcmPush");
                String jsonString = gson.toJson(fcmDetials);
                System.out.println("-----------HomeTabFragment fcmDetials : " + jsonString);
                System.out.println("-----------HomeTabFragment getTripStatus : " + fcmDetials.getTripStatus());
                System.out.println(" ----------- HomeTabFragment fcmPush : " + fcmPush);
                if (fcmPush != null && fcmPush.length() > 1 && fcmDetials.getUserName()!=null &&fcmDetials.getUserName().length() > 0) {
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
                        System.out.println("Pick x : "+x+" = "+pickUpLoc[x]);
                        pickLat = pickUpLoc[0];
                        picklng = pickUpLoc[1];
                    }
                    String[] dropLoc = fcmDetials.getDrop_loc().toString().split(",");
                    for (int x=0; x<dropLoc.length; x++) {
                        System.out.println("Drop x : "+x+" = "+dropLoc[x]);
                        dropLat = dropLoc[0];
                        droplng = dropLoc[1];
                    }
                    suddenTrip.setVisibility(View.VISIBLE);
                } else if (fcmDetials.getTripStatus().equals("Cancelled")) {
                    System.out.println("-----------HomeTabFragment getTripStatus : " + fcmDetials.getTripStatus());
                        suddenTrip.setVisibility(View.GONE);
                        iLandingView.hideViewsOnTripStartUp(View.VISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("-----------HomeTabFragment Exception" + e.getMessage().toString());
            }

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

    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
            case REQUEST_PHONE_SUPPORT_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9895184339"));
                    startActivity(intent);
                } else {

                }
                return;
            }
            case REQUEST_PHONE_CUSTOMER_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9895184339"));
                    startActivity(intent);
                } else {

                }
                return;
            }
        }
        updateLocationUI();
    }

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocationUI() {
        if (googleMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                googleMap.setMyLocationEnabled(false);
                googleMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        System.out.println("GoogleMap_CTL_lat-------" + lat + ",  CTL_log--------" + log);
        googleMap = map;
        getLocationPermission();
        googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        getActivity(), R.raw.map_style));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        setLocMethod();

        if (fcmAvliable != null) {
            // suddenTrip.setVisibility(View.VISIBLE);
        }


        //mMapView.getMapAsync(this);
    }

    public void setLocMethod(){
        googleMap.clear();
        googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, log)).title("Marker"));
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(lat, log));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
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
            throw new ClassCastException(context.toString() + " must implement ILandingView");
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
                break;
            case R.id.tripReject:
                iHomeTabPresenter.rejectTrip(driverUserModel.driverId, fcmDetials.getTripId());
                break;
            case R.id.tripSupport:
                System.out.println("callCustomerCare -- -  : ");
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "9895184339"));
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_SUPPORT_CALL);
                } else {
                    startActivity(callIntent);
                }
                break;
            case R.id.tripSupportCall:
                System.out.println("callCustomerCare -- -  : ");
                callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "9895184339"));
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_SUPPORT_CALL);
                } else {
                    startActivity(callIntent);
                }
                break;
            case R.id.tripCustomerCall:
                System.out.println("callCustomerCare -- -  : ");
                callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "9895184339"));
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CUSTOMER_CALL);
                } else {
                    startActivity(callIntent);
                }
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
        onTripStartUpLayout.setVisibility(View.VISIBLE);
        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        try {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            // get the last know location from your location manager.
            Location location= locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            // now get the lat/lon from the location and do something with it.
            if(location!=null) {
                currentLat = String.valueOf(location.getLatitude());
                currentLng = String.valueOf(location.getLongitude());
                iHomeTabPresenter.getToCustomerDirection(currentLat, currentLng, pickLat, picklng, false, ApiKey);
            }else {
                iHomeTabPresenter.getCurrentLocDetails();

            }
            System.out.println("currentLat: "+currentLat+", currentLng: "+currentLng);
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }



    }

    @Override
    public void driverStartTrip() {
        System.out.println("MapFragmentView setDriverRoutes, " +
                " destLat : " + currentLat +
                " ,destLong : " + currentLng +
                " ,sourceLat : " + dropLat +
                " ,sourceLong : " + droplng );
        iHomeTabPresenter.startOnGoingTrip(currentLat,currentLng,dropLat,droplng,false,ApiKey);
    }

    @Override
    public void rejectTrip() {
        suddenTrip.setVisibility(View.GONE);
        bundle.putString("fcmPush", null);
    }

    @Override
    public void stopedTrip() {
        onGoingTripLayout.setVisibility(View.GONE);
        iLandingView.hideViewsOnTripStartUp(View.VISIBLE);
        setLocMethod();
    }

    @Override
    public void hideViewsOnTrip(int visibility) {
        suddenTrip.setVisibility(visibility);
        iLandingView.hideViewsOnTripStartUp(visibility);
    }

    @OnClick(R.id.startTrip)
    public void startTripBtnClick(){
        onTripStartUpLayout.setVisibility(View.GONE);
        iHomeTabPresenter.startTrip(driverUserModel.driverId, fcmDetials.getTripId());
    }
    @OnClick(R.id.stopTrip)
    public void stopOnGoingTripBtnClick(){
        iHomeTabPresenter.endTrip(driverUserModel.driverId,fcmDetials.getTripId());
    }

    @Override
    public void setRoutesToCustomer(List<List<HashMap<String, String>>> route, List<Route> routes, String tripDist) {
        double pickUpLatD = Double.parseDouble(pickLat);
        double pickUpLngD = Double.parseDouble(picklng);
        double currentLatD = Double.parseDouble(currentLat);
        double currentLngD = Double.parseDouble(currentLng);

        if (currentLatD > 0.0 && currentLngD > 0.0 && pickUpLatD > 0.0 && pickUpLngD > 0.0) {

            System.out.println("MapFragmentView setRoutesToCustomer ---IF");
            if (googleMap != null) {
                System.out.println("HomeTabFragment acceptTrip currentLatD: "+ currentLatD +
                        ",currentLngD : "+ currentLngD +",pickUpLatD :"+pickUpLatD+",pickUpLngD: "+pickUpLngD);
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(currentLatD, currentLngD))
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
                    System.out.println("MapFragmentView setRoutesToCustomer---polyLineOptions --IF");
                    googleMap.addPolyline(polyLineOptions);
                } else {
                    Toast.makeText(getActivity(), "Could not find path", Toast.LENGTH_SHORT).show();
                }
            }else {
                System.out.println("MapFragmentView  setRoutesToCustomer --- no Map instance  --IF");
            }
        }
    }

    @Override
    public void setRoutesToDestination(List<List<HashMap<String, String>>> route, List<Route> routes, String tripDist) {
        double pickUpLatD = Double.parseDouble(dropLat);
        double pickUpLngD = Double.parseDouble(droplng);
        double currentLatD = Double.parseDouble(currentLat);
        double currentLngD = Double.parseDouble(currentLng);

        if (currentLatD > 0.0 && currentLngD > 0.0 && pickUpLatD > 0.0 && pickUpLngD > 0.0) {
            System.out.println("MapFragmentView  setRoutesToDestination---IF");
            if (googleMap != null) {
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(currentLatD, currentLngD))
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
                onGoingTripLayout.setVisibility(View.VISIBLE);
                if (polyLineOptions != null) {
                    System.out.println("MapFragmentView  setRoutesToDestination ---polyLineOptions --IF " );
                    googleMap.addPolyline(polyLineOptions);
                } else {
                    Toast.makeText(getActivity(), "Could not find path", Toast.LENGTH_SHORT).show();
                }
            }else {
                System.out.println("MapFragmentView  setRoutesToDestination --- no Map instance  --IF");
            }
        }
    }

    @Override
    public void highLikeHoodCurrentPlace(CurrentPlaceDetails maxCurrentPlaceDetails) {
        this.maxCurrentPlaceDetails = maxCurrentPlaceDetails;
        currentLat = maxCurrentPlaceDetails.getLat();
        currentLng =  maxCurrentPlaceDetails.getLng();
        iHomeTabPresenter.getToCustomerDirection(currentLat, currentLng, pickLat, picklng, false, ApiKey);
    }


}