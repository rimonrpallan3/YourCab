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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.common.Helper;
import com.voyager.sayaradriver.landingpage.presenter.LandingPresenter;
import com.voyager.sayaradriver.landingpage.view.ILandingView;
import com.voyager.sayaradriver.services.LocationService;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.tabfragment.earningstabfragment.EarningTabFragment;
import com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabFragment;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.ProfileTabFragment;
import com.voyager.sayaradriver.tabfragment.ratingstabfragment.RatingTabFragment;


/**
 * Created by User on 8/30/2017.
 */

 public class LandingPage extends AppCompatActivity implements View.OnClickListener, ILandingView {

    Activity activity;
    String online = "";
    String offline = "";

    SwitchCompat driverSwitch;
    TextView onlineOfflineTxt;
    LandingPresenter landingPresenter;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    DriverUserModel driverUserModel;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        activity = this;

        onlineOfflineTxt = (TextView) findViewById(R.id.onlineOfflineTxt);
        driverSwitch = (SwitchCompat) findViewById(R.id.driverSwitch);
        online = getString(R.string.driver_online);
        offline = getString(R.string.driver_offline);
        onlineOfflineTxt.setText(offline);

        sharedPrefs = getSharedPreferences(Helper.MyPREFERENCES,
                Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();
        Intent intent = getIntent();
        bundle = new Bundle();
        driverUserModel = (DriverUserModel) intent.getParcelableExtra("DriverUserModel");
        if (driverUserModel != null) {
            System.out.println("LandingPage -- DriverUserModel- name : " + driverUserModel.getFName());
        }
        else {
            getUserSDetails();
        }
        bundle.putParcelable("DriverUserModel", driverUserModel);
        landingPresenter = new LandingPresenter(this,driverUserModel);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeTabFragment(activity))
                    .commit();
        }
        driverSwitch.setOnClickListener(this);
        onlineOfflineTxt.setOnClickListener(this);

        driverSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                landingPresenter.checkOfflineOnline(isChecked,buttonView);
            }
        });
        intent = new Intent(getApplicationContext(), LocationService.class);
        startService(intent);
    }

    private void getUserSDetails() {
        Gson gson = new Gson();
        String json = sharedPrefs.getString("DriverUserModel",null);
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
        startService(intent);
    }

    @Override
    public void stopService() {
        Intent intent = new Intent(getApplicationContext(), LocationService.class);
        stopService(intent);
    }
}
