package com.voyager.sayaradriver.tabfragment.profiletabfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.voyager.sayaradriver.R;

import com.voyager.sayaradriver.about.AboutPage;
import com.voyager.sayaradriver.costom.CircleImageView;
import com.voyager.sayaradriver.displydoc.DisplayDoc;
import com.voyager.sayaradriver.drivehelp.DriverHelp;
import com.voyager.sayaradriver.profilepage.ProfileDetailPage;
import com.voyager.sayaradriver.signinpage.SignInPage;
import com.voyager.sayaradriver.signinpage.model.DriverUserModel;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.model.Documents;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.model.ProfileModel;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.presenter.IProfilePresenter;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.presenter.ProfileDetailPresenter;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.view.IProfileView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 9/8/2017.
 */

public class ProfileTabFragment  extends Fragment implements View.OnClickListener,IProfileView{


    ProfileModel profileModels;
    List<Documents> documentsList = new ArrayList<>();
    String yourFilePath ;

    TextView driverProfile;
    TextView profileHelp;
    TextView driverDoc;
    TextView driverSetting;
    TextView driverAbout;
    TextView editDriverCarImg;
    TextView editDriverImg;
    TextView profileDriverName;
    TextView profileDriverCarName;
    Button btnSignOut;
    IProfilePresenter iProfilePresenter;
    CircleImageView profileDriverImg;
    CircleImageView profileDriverCarImg;
    DriverUserModel driverUserModel;


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
        profileDriverImg = (CircleImageView) rootView.findViewById(R.id.profileDriverImg);
        profileDriverCarImg = (CircleImageView) rootView.findViewById(R.id.profileDriverCarImg);
        driverProfile = (TextView) rootView.findViewById(R.id.driverProfile);
        profileDriverCarName = (TextView) rootView.findViewById(R.id.profileDriverCarName);
        profileDriverName = (TextView) rootView.findViewById(R.id.profileDriverName);
        profileHelp = (TextView) rootView.findViewById(R.id.profileHelp);
        driverDoc = (TextView) rootView.findViewById(R.id.driverDoc);
        driverAbout = (TextView) rootView.findViewById(R.id.driverAbout);
        editDriverCarImg = (TextView) rootView.findViewById(R.id.driverAbout);
        editDriverImg = (TextView) rootView.findViewById(R.id.driverAbout);
        btnSignOut = (Button) rootView.findViewById(R.id.btnSignOut);

        driverProfile.setOnClickListener(this);
        profileHelp.setOnClickListener(this);
        driverDoc.setOnClickListener(this);
        driverAbout.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        editDriverCarImg.setOnClickListener(this);
        editDriverImg.setOnClickListener(this);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            driverUserModel = bundle.getParcelable("DriverUserModel");
            Gson gson = new Gson();
            String jsonString = gson.toJson(driverUserModel);
            System.out.println("-----------ProfileTabFragment DriverUserModel"+jsonString);
        }
        iProfilePresenter = new ProfileDetailPresenter(this,driverUserModel);
        iProfilePresenter.loadData();

        return rootView;
    }

    /**
     * This method creates an ArrayList that has Church Notification model class objects
     */
 /*   private List<MapDetails> initializeData(){
        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        mapDetailsList = new ArrayList<>();
        mapDetailsList.add(new MapDetails());
        return mapDetailsList;
    }
*/



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
                Intent intent = new Intent(getActivity(), ProfileDetailPage.class);
                intent.putExtra("ProfileModel", profileModels);
                startActivity(intent);
                break;
            case R.id.profileHelp:
                intent = new Intent(getActivity(), DriverHelp.class);
                intent.putExtra("ProfileModel", profileModels);
                startActivity(intent);
                break;
            case R.id.driverDoc:
                intent = new Intent(getActivity(), DisplayDoc.class);
                intent.putExtra("ProfileModel", profileModels);
                startActivity(intent);
                break;
            case R.id.driverAbout:
                intent = new Intent(getActivity(), AboutPage.class);
                intent.putExtra("ProfileModel", profileModels);
                startActivity(intent);
                break;
            case R.id.btnSignOut:
                iProfilePresenter.logout(1);
                break;

        }
    }

    @Override
    public void loadData(final List<ProfileModel> profileModel) {
        this.profileModels =profileModel.get(0);
        documentsList = profileModels.getDocuments();
        Picasso.with(getContext())
                .load(profileModels.getDriverPhoto())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .resize(0, 200)
                .into(profileDriverImg, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(getActivity())
                                .load(profileModels.getDriverPhoto())
                                .error(R.drawable.profile)
                                .resize(0, 200)
                                .into(profileDriverImg, new Callback() {
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
        Picasso.with(getContext())
                .load(profileModels.getCarPhoto())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .resize(0, 200)
                .into(profileDriverCarImg, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(getActivity())
                                .load(profileModels.getCarPhoto())
                                .error(R.drawable.profile)
                                .resize(0, 200)
                                .into(profileDriverCarImg, new Callback() {
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
        profileDriverName.setText(profileModels.getDriverName());
        profileDriverCarName.setText(profileModels.getCarName());
    }

    @Override
    public void logOut() {
        Intent intent = new Intent(getActivity(), SignInPage.class);
        startActivity(intent);
        getActivity().finish();
    }
}