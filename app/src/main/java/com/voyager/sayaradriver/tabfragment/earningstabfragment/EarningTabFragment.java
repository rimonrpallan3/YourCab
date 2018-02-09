package com.voyager.sayaradriver.tabfragment.earningstabfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.tabfragment.earningstabfragment.Model.EarningModel;
import com.voyager.sayaradriver.tabfragment.earningstabfragment.presenter.EarningPresenter;
import com.voyager.sayaradriver.tabfragment.earningstabfragment.presenter.IEarningPresenter;
import com.voyager.sayaradriver.tabfragment.earningstabfragment.view.IEarningView;


import java.util.List;

/**
 * Created by User on 9/8/2017.
 */

public class EarningTabFragment extends Fragment implements IEarningView{


    List<EarningModel> earningModels;
    String yourFilePath;
    TextView totalEarning;
    TextView trips;
    TextView balanceEarning;
    IEarningPresenter iEarningPresenter;


    private Activity activity;

    public EarningTabFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iEarningPresenter = new EarningPresenter(this);
        iEarningPresenter.loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.earning_tab_fragment, container, false);


        totalEarning = (TextView) rootView.findViewById(R.id.totalEarning);
        trips = (TextView) rootView.findViewById(R.id.trips);
        balanceEarning = (TextView) rootView.findViewById(R.id.balanceEarning);

        System.out.println("EarningTabFragment");


        return rootView;
    }

    /**
     * This method creates an ArrayList that has Church Notification model class objects
     */
    /*private List<MapDetails> initializeData(){
        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        mapDetailsList = new ArrayList<>();
        mapDetailsList.add(new MapDetails());
        return mapDetailsList;
    }*/




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


    @Override
    public void loadData(String trip, String totalEarn, String balanceEarn) {
        trips.setText(trip);
        totalEarning.setText(totalEarn);
        balanceEarning.setText(balanceEarn);
    }
}