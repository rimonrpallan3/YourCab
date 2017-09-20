package com.voyager.sayaradriver.landingpage;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.tabfragment.earningstabfragment.EarningTabFragment;
import com.voyager.sayaradriver.tabfragment.hometabfragment.HomeTabFragment;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.ProfileTabFragment;
import com.voyager.sayaradriver.tabfragment.ratingstabfragment.RatingTabFragment;


/**
 * Created by User on 8/30/2017.
 */

public class LandingPage extends AppCompatActivity {

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        activity = this;
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeTabFragment(activity))
                    .commit();
        }
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
}
