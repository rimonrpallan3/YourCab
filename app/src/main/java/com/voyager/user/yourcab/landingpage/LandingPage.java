package com.voyager.user.yourcab.landingpage;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.tabfragment.earningstabfragment.EarningTabFragment;
import com.voyager.user.yourcab.tabfragment.hometabfragment.HomeTabFragment;
import com.voyager.user.yourcab.tabfragment.profiletabfragment.ProfileTabFragment;
import com.voyager.user.yourcab.tabfragment.ratingstabfragment.RatingTabFragment;


/**
 * Created by User on 8/30/2017.
 */

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
    }

    public void homeTab(View v){
        Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
        HomeTabFragment homeTabFragment = new HomeTabFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, homeTabFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void earnTab(View v){
        Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
        EarningTabFragment earningTabFragment = new EarningTabFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, earningTabFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void rateTab(View v){
        Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
        RatingTabFragment ratingTabFragment = new RatingTabFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, ratingTabFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void profileTab(View v){
        Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
        ProfileTabFragment profileTabFragment = new ProfileTabFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, profileTabFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
