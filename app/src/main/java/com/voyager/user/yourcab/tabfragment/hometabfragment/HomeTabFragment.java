package com.voyager.user.yourcab.tabfragment.hometabfragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voyager.user.yourcab.R;
import com.voyager.user.yourcab.tabfragment.hometabfragment.model.MapDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rimon on 22/4/16.
 */
public class HomeTabFragment extends Fragment {


    List<MapDetails> mapDetailsList;
    String yourFilePath ;


    private Activity activity;

    public HomeTabFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.home_tab_fragment, container, false);
        System.out.println("Faith_Landing_Card_Church_VideoPlay_TabFragment");


          return rootView;
    }

    /**
     * This method creates an ArrayList that has Church Notification model class objects
     */
    private List<MapDetails> initializeData(){
        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        mapDetailsList = new ArrayList<>();
        mapDetailsList.add(new MapDetails());
        return mapDetailsList;
    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }



}