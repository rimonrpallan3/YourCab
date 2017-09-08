package com.voyager.user.yourcab.landingpage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.voyager.user.yourcab.tabfragment.earningstabfragment.EarningTabFragment;
import com.voyager.user.yourcab.tabfragment.hometabfragment.HomeTabFragment;
import com.voyager.user.yourcab.tabfragment.profiletabfragment.ProfileTabFragment;
import com.voyager.user.yourcab.tabfragment.ratingstabfragment.RatingTabFragment;

/**
 * Created by User on 9/8/2017.
 */

public class TabsPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HomeTabFragment tab1 = new HomeTabFragment();
                return tab1;
            case 1:
                EarningTabFragment tab2 = new EarningTabFragment();
                return tab2;
            case 3:
                RatingTabFragment tab3 = new RatingTabFragment();
                return tab3;
            case 4:
                ProfileTabFragment tab4 = new ProfileTabFragment();
                return tab4;



            default:
                HomeTabFragment tab7 = new HomeTabFragment();
                return tab7;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}