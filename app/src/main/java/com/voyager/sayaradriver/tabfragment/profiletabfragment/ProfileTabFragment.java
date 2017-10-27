package com.voyager.sayaradriver.tabfragment.profiletabfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.voyager.sayaradriver.DocumentPage.DocumentPage;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.registersuccess.RegisterSuccessPage;
import com.voyager.sayaradriver.signinpage.SignInPage;
import com.voyager.sayaradriver.tabfragment.hometabfragment.model.MapDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 9/8/2017.
 */

public class ProfileTabFragment  extends Fragment implements View.OnClickListener{


    List<MapDetails> mapDetailsList;
    String yourFilePath ;

    TextView driverProfile;
    TextView profileHelp;
    TextView driverDoc;
    TextView driverSetting;
    TextView driverAbout;
    TextView editDriverCarImg;
    TextView editDriverImg;
    Button btnSignOut;


    private Activity activity;

    public ProfileTabFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.profile_tab_fragment, container, false);
        System.out.println("ProfileTabFragment");
        driverProfile = (TextView) rootView.findViewById(R.id.driverProfile);
        profileHelp = (TextView) rootView.findViewById(R.id.profileHelp);
        driverDoc = (TextView) rootView.findViewById(R.id.driverDoc);
        driverSetting = (TextView) rootView.findViewById(R.id.driverSetting);
        driverAbout = (TextView) rootView.findViewById(R.id.driverAbout);
        editDriverCarImg = (TextView) rootView.findViewById(R.id.driverAbout);
        editDriverImg = (TextView) rootView.findViewById(R.id.driverAbout);
        btnSignOut = (Button) rootView.findViewById(R.id.btnSignOut);

        driverProfile.setOnClickListener(this);
        profileHelp.setOnClickListener(this);
        driverDoc.setOnClickListener(this);
        driverSetting.setOnClickListener(this);
        driverAbout.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        editDriverCarImg.setOnClickListener(this);
        editDriverImg.setOnClickListener(this);

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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.driverProfile:
               break;
            case R.id.profileHelp:
                break;
            case R.id.driverDoc:
                break;
            case R.id.driverSetting:
                break;
            case R.id.driverAbout:
                break;
            case R.id.btnSignOut:
                Intent intent = new Intent(getActivity(), SignInPage.class);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.editDriverCarImg:
                break;
            case R.id.editDriverImg:
                break;

        }
    }
}