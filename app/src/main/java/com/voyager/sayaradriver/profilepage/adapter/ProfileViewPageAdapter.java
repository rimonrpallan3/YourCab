package com.voyager.sayaradriver.profilepage.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.voyager.sayaradriver.tabfragment.profiletabfragment.model.ProfileModel;
import com.voyager.sayaradriver.viewpgertab.DriverProfileContent.DriverProfileContent;
import com.voyager.sayaradriver.viewpgertab.DriverVehicleContent.DriverVehicleContent;

/**
 * Created by User on 19-Feb-18.
 */

public class ProfileViewPageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Activity activity;
    ProfileModel profileModel;

    public ProfileViewPageAdapter(Activity activity, FragmentManager fm, int NumOfTabs, ProfileModel profileModel) {
        super(fm);
        this.activity = activity;
        this.mNumOfTabs = NumOfTabs;
        this.profileModel = profileModel;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:

                DriverProfileContent driverProfileContent = new DriverProfileContent(profileModel);
                return driverProfileContent;
            case 1:

                DriverVehicleContent driverVehicleContent = new DriverVehicleContent(profileModel);
                return driverVehicleContent;
            default:
                 driverProfileContent = new DriverProfileContent(profileModel);
                return driverProfileContent;

        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }




}