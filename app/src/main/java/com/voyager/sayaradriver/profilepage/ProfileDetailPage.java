package com.voyager.sayaradriver.profilepage;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.profilepage.adapter.ProfileViewPageAdapter;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.model.ProfileModel;

/**
 * Created by User on 19-Feb-18.
 */

public class ProfileDetailPage  extends AppCompatActivity {

    ViewPager viewPagerDriver;
    TabLayout tabLayoutDriver;
    Toolbar toolbarProfileDetail;

    ProfileViewPageAdapter profileViewPageAdapter;
    ProfileModel profileModel;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_detail_page);
        toolbarProfileDetail = (Toolbar) findViewById(R.id.toolbarProfileDetail);

        setSupportActionBar(toolbarProfileDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.driver_profile_title));
        toolbarProfileDetail.setTitleTextColor(ContextCompat.getColor(this, R.color.black));

        Intent intent = getIntent();
        bundle = new Bundle();
        profileModel = (ProfileModel) intent.getParcelableExtra("ProfileModel");
        if (profileModel != null) {
            System.out.println("ProfileDetailPage -- ProfileModel- driverId : " + profileModel.getDriverId());
        }

        tabLayoutDriver = (TabLayout) findViewById(R.id.tabLayoutDriver);
        //tabLayoutDriver.addTab(tabLayoutDriver.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayoutDriver.addTab(tabLayoutDriver.newTab().setText("Driver Details"));
        //tabLayoutDriver.addTab(tabLayoutDriver.newTab().setIcon(R.drawable.ic_home_white_24dp));
        tabLayoutDriver.addTab(tabLayoutDriver.newTab().setText("Vehicle Details"));
        tabLayoutDriver.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPagerDriver = (ViewPager) findViewById(R.id.viewPagerDriver);
        profileViewPageAdapter = new ProfileViewPageAdapter
                (ProfileDetailPage.this, getSupportFragmentManager(), tabLayoutDriver.getTabCount(),profileModel);
        viewPagerDriver.setOffscreenPageLimit(2);
        viewPagerDriver.setAdapter(profileViewPageAdapter);
        viewPagerDriver.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutDriver));

        tabLayoutDriver.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerDriver.setCurrentItem(tab.getPosition());
              //  tab.getCustomView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.highLighted));
                if (tab.getPosition() == 1) {
                    //faith_search_edit_text.setAdapter(suggestionAdapter);

                }
                if (tab.getPosition() == 0) {
                    try{
                        //hideSoftKeyboard();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
              //  viewPagerDriver.setCurrentItem(tab.getPosition());
               // tab.getCustomView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.UnHighlighted));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
