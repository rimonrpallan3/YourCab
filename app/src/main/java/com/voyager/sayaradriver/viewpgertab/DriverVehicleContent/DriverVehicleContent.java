package com.voyager.sayaradriver.viewpgertab.DriverVehicleContent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.voyager.sayaradriver.R;
import com.voyager.sayaradriver.costom.CircleImageView;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.model.ProfileModel;

/**
 * Created by User on 20-Feb-18.
 */

public class DriverVehicleContent extends Fragment {
    ProfileModel profileModel;
    CircleImageView vehicleImg;
    TextView vehicleName;
    TextView vehicleRegNo;
    TextView vehicleTaxExpDate;
    TextView vehicleInsExpDate;
    TextView vehiclePollExpDate;
    TextView vehicleOwnerName;

    public DriverVehicleContent(ProfileModel profileModel) {
        this.profileModel = profileModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.driver_vehicle_content, container, false);
        System.out.println("DriverVehicleContent");
        vehicleImg = (CircleImageView)  rootView.findViewById(R.id.vehicleImg);
        vehicleName = (TextView)  rootView.findViewById(R.id.vehicleName);
        vehicleRegNo = (TextView)  rootView.findViewById(R.id.vehicleRegNo);
        vehicleTaxExpDate = (TextView)  rootView.findViewById(R.id.vehicleTaxExpDate);
        vehicleInsExpDate = (TextView)  rootView.findViewById(R.id.vehicleInsExpDate);
        vehiclePollExpDate = (TextView)  rootView.findViewById(R.id.vehiclePollExpDate);
        vehicleOwnerName = (TextView)  rootView.findViewById(R.id.vehicleOwnerName);
        vehicleName.setText(profileModel.getCarName());
        Picasso.with(getContext())
                .load(profileModel.getCarPhoto())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .resize(0, 200)
                .into(vehicleImg, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(getActivity())
                                .load(profileModel.getCarPhoto())
                                .error(R.drawable.profile)
                                .resize(0, 200)
                                .into(vehicleImg, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso","Could not fetch image");
                                    }
                                });
                    }
                });
        vehicleRegNo.setText(profileModel.getDriverCarRegNo());
        vehicleTaxExpDate.setText(profileModel.getDriverCarTaxExpiryDate());
        vehicleInsExpDate.setText(profileModel.getDriverCarInsuranceExpiryDate());
        vehiclePollExpDate.setText(profileModel.getDriverCarPollutionExpiryDate());
        vehicleOwnerName.setText(profileModel.getDriverCarOwner());
        return rootView;
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