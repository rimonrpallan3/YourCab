package com.voyager.sayaradriver.landingpage;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.tabfragment.earningstabfragment.EarningTabFragment;
import com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabFragment;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.ProfileTabFragment;
import com.voyager.sayaradriver.tabfragment.ratingstabfragment.RatingTabFragment;


/**
 * Created by User on 8/30/2017.
 */

public class LandingPage extends AppCompatActivity implements View.OnClickListener {

    Activity activity;

   String online = "";
   String offline = "";

    SwitchCompat driverSwitch;
    TextView onlineOfflineTxt;



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

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeTabFragment(activity))
                    .commit();
        }
        driverSwitch.setOnClickListener(this);
        onlineOfflineTxt.setOnClickListener(this);

        driverSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("driverSwitch Clicked"+"yehhh!!");
                if (isChecked){
                    System.out.println("driverSwitch Clicked :"+" true!!");
                    onlineOfflineTxt.setText(online);
                }
                else{
                    System.out.println("driverSwitch Clicked :"+" false!!");
                    onlineOfflineTxt.setText(offline);
                }
            }
        });
    }

    public void homeTab(View v){
        Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
        HomeTabFragment homeTabFragment = new HomeTabFragment(activity);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, homeTabFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void earnTab(View v){
        Toast.makeText(getApplicationContext(), "Earn Selected", Toast.LENGTH_SHORT).show();
        EarningTabFragment earningTabFragment = new EarningTabFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, earningTabFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void rateTab(View v){
        Toast.makeText(getApplicationContext(), "Rate Selected", Toast.LENGTH_SHORT).show();
        RatingTabFragment ratingTabFragment = new RatingTabFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, ratingTabFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void profileTab(View v){
        Toast.makeText(getApplicationContext(), "Profile Selected", Toast.LENGTH_SHORT).show();
        ProfileTabFragment profileTabFragment = new ProfileTabFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, profileTabFragment);
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
}
