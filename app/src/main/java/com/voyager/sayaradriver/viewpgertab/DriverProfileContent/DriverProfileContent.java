package com.voyager.sayaradriver.viewpgertab.DriverProfileContent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class DriverProfileContent  extends Fragment {

    ProfileModel profileModel;
    CircleImageView driverImg;
    TextView driverName;
    TextView mobNo;
    TextView emailId;
    TextView bankAcc;
    TextView CPRNo;
    public DriverProfileContent(ProfileModel profileModel) {
         this.profileModel = profileModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.driver_content, container, false);
        driverImg = (CircleImageView)  rootView.findViewById(R.id.driverImg);
        driverName = (TextView)  rootView.findViewById(R.id.userName);
        mobNo = (TextView)  rootView.findViewById(R.id.mobNo);
        emailId = (TextView)  rootView.findViewById(R.id.emailId);
        bankAcc = (TextView)  rootView.findViewById(R.id.bankAcc);
        CPRNo = (TextView)  rootView.findViewById(R.id.CPRNo);
        driverName.setText(profileModel.getDriverName());
        Picasso.with(getContext())
                .load(profileModel.getDriverPhoto())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .resize(0, 200)
                .into(driverImg, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(getActivity())
                                .load(profileModel.getDriverPhoto())
                                .error(R.drawable.profile)
                                .resize(0, 200)
                                .into(driverImg, new Callback() {
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
        mobNo.setText(profileModel.getDriverPhone());
        emailId.setText(profileModel.getDriverEmail());
        bankAcc.setText(profileModel.getDriverAcno());
        CPRNo.setText(profileModel.getDriverCpr());
        System.out.println("DriverProfileContent");


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