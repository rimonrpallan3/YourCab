package com.voyager.sayaradriver.landingpage;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.common.Helper;
import com.voyager.sayaradriver.landingpage.presenter.ILandingPresenter;
import com.voyager.sayaradriver.landingpage.presenter.LandingPresenter;
import com.voyager.sayaradriver.landingpage.view.ILandingView;
import com.voyager.sayaradriver.services.LocationService;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.tabfragment.earningstabfragment.EarningTabFragment;
import com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabFragment;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.FCMDetials;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.ProfileTabFragment;
import com.voyager.sayaradriver.tabfragment.ratingstabfragment.RatingTabFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by User on 8/30/2017.
 */

 public class LandingPage extends AppCompatActivity implements View.OnClickListener, ILandingView {

    Activity activity;
    String online = "";
    String offline = "";

    SwitchCompat driverSwitch;
    TextView onlineOfflineTxt;
    ILandingPresenter iLandingPresenter;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    DriverUserModel driverUserModel;
    Bundle bundle;
    String fcmAvliable = "";
    String fcmPush = "";
    FCMDetials fcmDetials;
    @BindView(R.id.tabItemsLayout)
    LinearLayout tabItemsLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        activity = this;
        ButterKnife.bind(this);
        onlineOfflineTxt = (TextView) findViewById(R.id.onlineOfflineTxt);
        driverSwitch = (SwitchCompat) findViewById(R.id.driverSwitch);
        online = getString(R.string.driver_online);
        offline = getString(R.string.driver_offline);
        onlineOfflineTxt.setText(offline);

        sharedPrefs = getSharedPreferences(getResources().getString(R.string.myPreference),
                Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();
        Intent intent = getIntent();
        bundle = new Bundle();
        driverUserModel = (DriverUserModel) intent.getParcelableExtra("DriverUserModel");
        fcmAvliable = bundle.getString("showNotification",fcmAvliable);
        if (driverUserModel != null) {
            System.out.println("LandingPage -- DriverUserModel- name : " + driverUserModel.getFName());
        }
        else {
            getUserSDetails();
        }
        bundle.putParcelable("DriverUserModel", driverUserModel);
        if(driverUserModel!=null) {
            iLandingPresenter = new LandingPresenter(this, driverUserModel, online, offline);
        }else if(driverUserModel==null){
            getUserSDetails();
            iLandingPresenter = new LandingPresenter(this, driverUserModel, online, offline);
        }


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeTabFragment(activity))
                    .commit();
        }
        driverSwitch.setOnClickListener(this);
        onlineOfflineTxt.setOnClickListener(this);

        driverSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iLandingPresenter.checkOfflineOnline(isChecked,buttonView);
            }
        });
        /*intent = new Intent(getApplicationContext(), LocationService.class);
        startService(intent);*/
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("onNewIntent Landing -------------");
        fcmDetials = (FCMDetials) intent.getParcelableExtra("FCMDetials");
        fcmPush =  intent.getStringExtra("fcmPush");
        System.out.println("onNewIntent Landing fcmPush-------------  "+fcmPush);
        bundle.putParcelable("FCMDetials", fcmDetials);
        bundle.putString("fcmPush",fcmPush);
        if(fcmDetials!=null){
            System.out.println("onNewIntent Landing ------------- inside  ");
            Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
            HomeTabFragment homeTabFragment = new HomeTabFragment(activity);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, homeTabFragment);
            homeTabFragment.setArguments(bundle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }

    private void getUserSDetails() {
        Gson gson = new Gson();
        String json = sharedPrefs.getString(getResources().getString(R.string.myPreference),null);
        if(json!=null){
            System.out.println("-----------LandingPage uploadProfileName DriverUserModel" + json);
            driverUserModel = gson.fromJson(json, DriverUserModel.class);
        }

    }

    public void homeTab(View v){
        Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
        HomeTabFragment homeTabFragment = new HomeTabFragment(activity);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, homeTabFragment);
        homeTabFragment.setArguments(bundle);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void earnTab(View v){
        Toast.makeText(getApplicationContext(), "Earn Selected", Toast.LENGTH_SHORT).show();
        EarningTabFragment earningTabFragment = new EarningTabFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, earningTabFragment);
        earningTabFragment.setArguments(bundle);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void rateTab(View v){
        Toast.makeText(getApplicationContext(), "Rate Selected", Toast.LENGTH_SHORT).show();
        RatingTabFragment ratingTabFragment = new RatingTabFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, ratingTabFragment);
        ratingTabFragment.setArguments(bundle);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void profileTab(View v){
        Toast.makeText(getApplicationContext(), "Profile Selected", Toast.LENGTH_SHORT).show();
        ProfileTabFragment profileTabFragment = new ProfileTabFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, profileTabFragment);
        profileTabFragment.setArguments(bundle);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.driverSwitch:
                break;
        }

    }

    @Override
    public void getOfflineOnlineState(String setText) {
        onlineOfflineTxt.setText(setText);
    }

    @Override
    public void startService() {
        Intent intent = new Intent(getApplicationContext(), LocationService.class);
        intent.putExtra("DriverUserModel", driverUserModel);
        startService(intent);
    }

    @Override
    public void stopService() {
        Intent intent = new Intent(getApplicationContext(), LocationService.class);
        stopService(intent);
    }

    @Override
    public void addUserGsonInSharedPrefrences(DriverUserModel driverUserModel) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(driverUserModel);
        //DriverUserModel user1 = gson.fromJson(jsonString,DriverUserModel.class);
        if(jsonString!=null) {
            editor.putString("DriverUserModel", jsonString);
            editor.commit();
        }
    }

    @Override
    public void hideViewsOnTripStartUp(int visibility) {
        tabItemsLayout.setVisibility(visibility);
    }

}
